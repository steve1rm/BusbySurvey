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

            /** If we don't have a token then we are not currently logged in and don't have a
             *  valid token to request surveys */
            if(authorizationInfo != null) {
                /** Check if there are any items in the local DB */
                if(fetchLocalSurveyListUseCase.execute().first().isEmpty()) {
                    /** If the local DB is empty then load from the network and write to the local DB */
                    Timber.d("FETCHED ${MainViewModel::class.simpleName} DB is Empty")

                    when (val apiResponse = fetchSurveyListUseCase.execute()) {
                        is CheckResult.Success -> {
                            /** We are setting loading the DB, then we should haven't to do this, as we are listening for changes in the HomeViewModel
                             *  when we observe the realm DB */
                            apiResponse.data.data.forEach { dataModel ->
                                Timber.d("FETCH SPLASH SCREEN LOGGED IN ${dataModel.attributes.title}")
                                writeLocalSurveyListUseCase.execute(dataModel.attributes.title, dataModel.attributes.description, dataModel.attributes.coverImageUrl)
                            }
                        }

                        is CheckResult.Failure -> {
                            Timber.d("Survey %s %s", apiResponse.exceptionError, apiResponse.responseError?.errors?.first()?.detail)
                        }
                    }
                }
            }

            mainState = mainState.copy(
                isLoggedIn = authorizationInfo != null)

            mainState = mainState.copy(isCheckingAuthorization = false)
        }
    }
}