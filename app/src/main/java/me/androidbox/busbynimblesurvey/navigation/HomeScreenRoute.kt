package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.busbynimblesurvey.MainViewModel
import me.androidbox.presentation.home.HomeAction
import me.androidbox.presentation.home.HomeScreen
import me.androidbox.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

data object HomeScreenRoute : Screen {

    @Composable
    override fun Content() {
        val homeViewModel = koinViewModel<HomeViewModel>()
        val mainViewModel = koinViewModel<MainViewModel>()

        val homeState = homeViewModel.homeState
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = true) {
            if(mainViewModel.mainState.surveyListModel.data.isNotEmpty()) {
                homeViewModel.homeAction(HomeAction.FetchFromSplash(mainViewModel.mainState.surveyListModel))
            }
            else {
                homeViewModel.homeAction(HomeAction.FetchFromNetwork)
            }
        }

        HomeScreen(
            onForwardButtonClicked = {
                navigator.push(SurveyStartScreenRoute)
            },
            onLogoutSuccess = {
                navigator.replaceAll(LoginScreenRoute)
            },
            homeState = homeState,
            onHomeAction = homeViewModel::homeAction
        )
    }
}