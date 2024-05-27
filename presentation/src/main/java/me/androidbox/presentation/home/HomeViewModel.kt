package me.androidbox.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.survey.models.SurveyListModel
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import timber.log.Timber

class HomeViewModel(
    private val fetchSurveyListUseCase: FetchSurveyListUseCase
) : ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    private fun fillSurveyList(surveyListModel: SurveyListModel) {
        homeState = homeState.copy(
            homeItems = surveyListModel.data.map { data ->
                HomeItems(
                    title = data.attributes.title,
                    description = data.attributes.description,
                    imageUrl = data.attributes.coverImageUrl)
            })
    }

    private fun fetchSurveyList() {
        viewModelScope.launch {
            homeState = homeState.copy(
                isLoading = true
            )

            when (val apiResponse = fetchSurveyListUseCase.execute()) {
                is APIResponse.OnSuccess -> {
                    homeState = homeState.copy(
                        homeItems = apiResponse.data.data.map { data ->
                            HomeItems(
                                title = data.attributes.title,
                                description = data.attributes.description,
                                imageUrl = data.attributes.coverImageUrl
                            )
                        }
                    )
                    Timber.d("HomeViewModel ${homeState.homeItems.count()}")
                }

                is APIResponse.OnFailure -> {
                    /** Show error cannot load data */
                }
            }

            homeState = homeState.copy(
                isLoading = false
            )
        }
    }

    fun homeAction(homeAction: HomeAction) {
        when(homeAction) {
            is HomeAction.FetchFromNetwork -> {
                fetchSurveyList()
            }
            is HomeAction.FetchFromSplash -> {
                fillSurveyList(homeAction.surveyListModel)
            }
        }
    }
}

