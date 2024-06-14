package me.androidbox.busbynimblesurvey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.domain.CheckResult
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import timber.log.Timber

class MainViewModel(
    private val fetchTokenAuthorizationUseCase: FetchTokenAuthorizationUseCase,
    private val fetchSurveyListUseCase: FetchSurveyListUseCase
) : ViewModel() {

    var mainState by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            mainState = mainState.copy(
                isCheckingAuthorization = true)

            val authorizationInfo = fetchTokenAuthorizationUseCase.execute()
            Timber.d("FETCHED Authorization ${authorizationInfo?.accessToken}")

            /** If we don't have a token then we are not currently logged in and don't have a
             *  valid token to request surveys */
            if(authorizationInfo != null) {
                when (val apiResponse = fetchSurveyListUseCase.execute()) {
                    is CheckResult.Success -> {
                        mainState = mainState.copy(
                            surveyListModel = apiResponse.data
                        )
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