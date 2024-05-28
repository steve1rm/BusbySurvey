package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.domain.survey.models.SurveyListModel
import me.androidbox.presentation.home.HomeAction
import me.androidbox.presentation.home.HomeScreen
import me.androidbox.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel


data class HomeScreenRoute(private val surveyListModel: SurveyListModel = SurveyListModel()) : Screen {

    @Composable
    override fun Content() {
        val homeViewModel = koinViewModel<HomeViewModel>()
        val homeState = homeViewModel.homeState
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = true) {
            if(surveyListModel.data.isNotEmpty()) {
                homeViewModel.homeAction(HomeAction.FetchFromSplash(surveyListModel))
            }
            else {
                homeViewModel.homeAction(HomeAction.FetchFromNetwork)
            }
        }

        HomeScreen(
            onForwardButtonClicked = {
                navigator.push(SurveyStartScreenRoute)
            },
            homeState = homeState
        )
    }
}