package me.androidbox.presentation.authentication.reset

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.presentation.components.GradientBackground
import org.koin.androidx.compose.koinViewModel

object ResetScreenRoute : Screen {

    @Composable
    override fun Content() {
        val resetPasswordViewModel = koinViewModel<ResetPasswordViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        GradientBackground {
            ResetScreen(
                resetPasswordState = resetPasswordViewModel.resetPasswordState,
                onBackPressed = {
                    navigator.pop()
                },
                onResetPasswordAction = {}
            )
        }
    }
}