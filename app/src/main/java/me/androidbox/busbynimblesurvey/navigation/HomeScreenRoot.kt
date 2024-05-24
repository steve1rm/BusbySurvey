package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.presentation.home.HomeScreen
import me.androidbox.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

object HomeScreenRoot : Screen {

    @Composable
    override fun Content() {
        val homeViewModel = koinViewModel<HomeViewModel>()
        val homeState = homeViewModel.homeState

        HomeScreen(
            onHomeAction = homeViewModel::homeAction,
            homeState = homeState
        )
    }
}