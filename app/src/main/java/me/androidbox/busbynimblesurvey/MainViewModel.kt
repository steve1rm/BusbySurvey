package me.androidbox.busbynimblesurvey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import me.androidbox.domain.CheckResult
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import me.androidbox.domain.survey.usecases.FetchLocalSurveyListUseCase
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.domain.survey.usecases.WriteLocalSurveyListUseCase
import timber.log.Timber

class MainViewModel(
    private val fetchTokenAuthorizationUseCase: FetchTokenAuthorizationUseCase,
    private val fetchSurveyListUseCase: FetchSurveyListUseCase,
    private val fetchLocalSurveyListUseCase: FetchLocalSurveyListUseCase,
    private val writeLocalSurveyListUseCase: WriteLocalSurveyListUseCase
) : ViewModel() {

    var mainState by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            mainState = mainState.copy(
                isCheckingAuthorization = true)

            val authorizationInfo = fetchTokenAuthorizationUseCase.execute()
            Timber.d("FETCHED Authorization ${authorizationInfo?.accessToken}")

            /** Check if there are any items in the local DB */
            if(fetchLocalSurveyListUseCase.execute().first().isNotEmpty()) {
                /** If the local DB contains survey list then load from the local DB */
                Timber.d("FETCHED Local Not Empty ${fetchLocalSurveyListUseCase.execute().first().count()}")
            }
            else {
                /** If the local DB is empty then load from the network and write to the local DB */
                Timber.d("FETCHED Local Empty ${fetchLocalSurveyListUseCase.execute().first().count()}")

            }

            /** If we don't have a token then we are not currently logged in and don't have a
             *  valid token to request surveys */
            if(authorizationInfo != null) {
                when (val apiResponse = fetchSurveyListUseCase.execute()) {
                    is CheckResult.Success -> {
                        mainState = mainState.copy(
                            surveyListModel = apiResponse.data
                        )
                        val title = apiResponse.data.data.first().attributes.title
                        val description = apiResponse.data.data.first().attributes.description
                        val imageUrl = apiResponse.data.data.first().attributes.coverImageUrl

                        writeLocalSurveyListUseCase.execute(title, description, imageUrl)
                    }

                    is CheckResult.Failure -> {
                        Timber.d("Survey %s %s", apiResponse.exceptionError, apiResponse.responseError?.errors?.first()?.detail)
                    }
                }
                Timber.d("FETCHED SurveyList")
            }

            mainState = mainState.copy(
                isLoggedIn = authorizationInfo != null)

            mainState = mainState.copy(isCheckingAuthorization = false)
        }
    }
}