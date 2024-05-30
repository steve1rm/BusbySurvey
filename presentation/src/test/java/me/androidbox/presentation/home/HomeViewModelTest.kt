package me.androidbox.presentation.home

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.survey.models.AttributesModel
import me.androidbox.domain.survey.models.DataModel
import me.androidbox.domain.survey.models.SurveyListModel
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class HomeViewModelTest {
    private val fetchSurveyListUseCase: FetchSurveyListUseCase = mock(FetchSurveyListUseCase::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `should fetch from the network`() = runTest {
        // Arrange
        val homeViewModel = HomeViewModel(fetchSurveyListUseCase)
        whenever(fetchSurveyListUseCase.execute()).thenReturn(
            APIResponse.OnSuccess(SurveyListModel(
                data = listOf(DataModel(AttributesModel("", "", "", "", "", true, "", "", "", ""), "", "")))))

        // Act
        homeViewModel.homeAction(HomeAction.FetchFromNetwork)

        // Assert
        assertThat(homeViewModel.homeState.isLoading).isFalse()
    }
}