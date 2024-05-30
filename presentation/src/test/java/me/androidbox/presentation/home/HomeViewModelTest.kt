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
import java.util.UUID

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
        val title = UUID.randomUUID().toString()
        val description = UUID.randomUUID().toString()
        val coverImageUrl = UUID.randomUUID().toString()

        val homeViewModel = HomeViewModel(fetchSurveyListUseCase)
        whenever(fetchSurveyListUseCase.execute()).thenReturn(
            APIResponse.OnSuccess(SurveyListModel(
                data = listOf(DataModel(AttributesModel("", coverImageUrl, "", description, "", true, "", "", "", title), "", "")))))

        // Act
        homeViewModel.homeAction(HomeAction.FetchFromNetwork)

        // Assert

        assertThat(homeViewModel.homeState.homeItems.first().title).isEqualTo(title)
        assertThat(homeViewModel.homeState.homeItems.first().description).isEqualTo(description)
        assertThat(homeViewModel.homeState.homeItems.first().imageUrl).isEqualTo(coverImageUrl)
    }
}