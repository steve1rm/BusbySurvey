package me.androidbox.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.androidbox.domain.CheckResult
import me.androidbox.domain.authorization.usecases.LogoutUserUseCase
import me.androidbox.domain.authorization.usecases.SetTokenAuthorizationUseCase
import me.androidbox.domain.local.SurveyListLocalModel
import me.androidbox.domain.survey.usecases.DeleteLocalSurveyListUseCase
import me.androidbox.domain.survey.usecases.FetchLocalSurveyListUseCase
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.domain.survey.usecases.WriteLocalSurveyListUseCase
import timber.log.Timber

class HomeViewModel(
    private val fetchSurveyListUseCase: FetchSurveyListUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val setTokenAuthorizationUseCase: SetTokenAuthorizationUseCase,
    private val fetchLocalSurveyListUseCase: FetchLocalSurveyListUseCase,
    private val writeLocalSurveyListUseCase: WriteLocalSurveyListUseCase,
    private val deleteLocalSurveyListUseCase: DeleteLocalSurveyListUseCase
) : ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    init {
        HomeViewModel::class.simpleName?.let {
            Timber.tag(it)
        }
        viewModelScope.launch {
            if (fetchLocalSurveyListUseCase.execute().first().isEmpty()) {
                fetchSurveyList()
            }

            /** Observing changes in the DB */
            fetchLocalSurveyListUseCase.execute()
                .onEach { listOfSurveys: List<SurveyListLocalModel> ->
                    homeState = homeState.copy(
                        homeItems = listOfSurveys.map { survey ->
                            Timber.d("FETCHED DB LOCAL ITEM ${HomeViewModel::class.simpleName} ${survey.title}")
                            HomeItems(
                                title = survey.title,
                                description = survey.description,
                                imageUrl = survey.imageUrl
                            )
                        }
                    )
                }
                .launchIn(this)
        }
    }

    private suspend fun fetchSurveyList() {
        if(homeState.homeItems.isEmpty()) {
            homeState = homeState.copy(
                isLoading = true
            )

            when (val apiResponse = fetchSurveyListUseCase.execute()) {
                is CheckResult.Success -> {
                    apiResponse.data.data.forEach { dataModel ->
                        Timber.d("FETCH ${HomeViewModel::class.simpleName} Network ${dataModel.attributes.title}")
                        writeLocalSurveyListUseCase.execute(dataModel.attributes.title, dataModel.attributes.description, dataModel.attributes.coverImageUrl)
                    }
                }

                is CheckResult.Failure -> {
                    Timber.d("Survey %s %s", apiResponse.exceptionError, apiResponse.responseError?.errors?.first()?.detail)
                }
            }

            homeState = homeState.copy(
                isLoading = false
            )
        }
    }

    fun homeAction(homeAction: HomeAction) {
        when(homeAction) {
            is HomeAction.LogoutUser -> {
                homeState = homeState.copy(
                    showShowDialog = true
                )
            }
            is HomeAction.CancelLogout -> {
                homeState = homeState.copy(
                    showShowDialog = false
                )
            }
            is HomeAction.ContinueLogout -> {
                homeState = homeState.copy(showShowDialog = false)
                logoutUser()
            }
        }
    }

    private fun logoutUser() {
        viewModelScope.launch {
            homeState = homeState.copy(
                isLoading = true,
                isSuccessLogout = false
            )

            logoutUserUseCase.execute()
            Timber.d("HomeViewModel logout executed completed")
            setTokenAuthorizationUseCase.execute(null)
            Timber.d("HomeViewModel delete authorization executed completed")
            deleteLocalSurveyListUseCase.execute()
            Timber.d("HomeViewModel delete survey executed completed")

            homeState = homeState.copy(isLoading = false)
            homeState = homeState.copy(isSuccessLogout = true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("OnCleared ${HomeViewModel::class.simpleName}")
    }
}

