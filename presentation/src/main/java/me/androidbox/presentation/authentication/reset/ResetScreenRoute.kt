package me.androidbox.presentation.authentication.reset

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object ResetScreenRoute : Screen {

    @Composable
    override fun Content() {
        ResetScreen(
            onBackPressed = {

            }
        )
    }
}