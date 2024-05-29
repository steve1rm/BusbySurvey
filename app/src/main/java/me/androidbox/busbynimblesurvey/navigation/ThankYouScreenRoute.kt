package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.presentation.thankyou.ThankYouScreen

data object ThankYouScreenRoute : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        ThankYouScreen(onTerminateScreen = {
            navigator.pop()
        })
    }
}