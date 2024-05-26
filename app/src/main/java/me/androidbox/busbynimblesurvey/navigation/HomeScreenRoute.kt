package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.presentation.home.HomeScreen
import me.androidbox.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

object HomeScreenRoute : Screen {

    @Composable
    override fun Content() {
        val homeViewModel = koinViewModel<HomeViewModel>()
        val homeState = homeViewModel.homeState
        val navigator = LocalNavigator.currentOrThrow

        HomeScreen(
            onForwardButtonClicked = {
                navigator.push(SurveyStartScreenRoute)
            },
            homeState = homeState
        )
    }
}