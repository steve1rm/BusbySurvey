package me.androidbox.busbynimblesurvey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import me.androidbox.domain.repository.APIResponse
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
            when(val surveyList = fetchSurveyListUseCase.execute()) {
                is APIResponse.OnSuccess -> {
                    mainState = mainState.copy(
                        surveyListModel = surveyList.data
                    )
                }
                is APIResponse.OnFailure -> {
                    /** Handle failed to get survey list */
                }
            }
            Timber.d("FETCHED SurveyList")

            mainState = mainState.copy(
                isLoggedIn = authorizationInfo != null)

            mainState = mainState.copy(isCheckingAuthorization = false)
        }
    }
}