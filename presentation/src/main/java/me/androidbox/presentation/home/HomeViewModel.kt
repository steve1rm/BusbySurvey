package me.androidbox.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import timber.log.Timber

class HomeViewModel(
    private val fetchSurveyListUseCase: FetchSurveyListUseCase
) : ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            homeState = homeState.copy(
                isLoading = true)

            when(val apiResponse = fetchSurveyListUseCase.execute()) {
                is APIResponse.OnSuccess -> {
                    homeState = homeState.copy(
                        homeItems = createHomeItems().map { data ->
                            HomeItems(
                                title = data.title,
                                description = data.description,
                                imageUrl = data.imageUrl
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
            is HomeAction.OnNextButtonClicked -> {

            }
        }
    }

    private fun createHomeItems(): List<HomeItems> {
        return listOf(HomeItems(
            title = "Scarlett Bangkok",
            description = "We'd love ot hear from you!",
            imageUrl = "https://ucarecdn.com/62886578-df8b-4f65-902e-8e88d97748b8/-/quality/smart_retina/-/format/auto/"
        ),

        HomeItems(
            title = "ibis Bangkok Riverside",
            description = "We'd love to hear from you!",
            imageUrl = "https://ucarecdn.com/ed35738a-31e0-476c-8af3-1cf3dfb92ad9/-/quality/smart_retina/-/format/auto/"
        ),

        HomeItems(
            title = "21 on Rajah",
            description = "We'd love to hear from you!",
            imageUrl = "https://ucarecdn.com/e4bc340d-27e8-4698-80f2-91e0e5c91a13/-/quality/smart_retina/-/format/auto/"
        ),

        HomeItems(
            title = "Let's Chick",
            description = "We'd love to hear from you!",
            imageUrl = "https://ucarecdn.com/456f46fe-637c-4ed1-9d6d-67f4fd0f2bde/-/quality/smart_retina/-/format/auto/"
        ))
    }
}

