package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.busbynimblesurvey.MainActivity
import me.androidbox.presentation.NotificationHandler
import me.androidbox.presentation.authentication.reset.ResetPasswordViewModel
import me.androidbox.presentation.authentication.reset.ResetPasswordScreen
import me.androidbox.presentation.components.GradientBackground
import org.koin.androidx.compose.koinViewModel

object ResetScreenRoute : Screen {

    @Composable
    override fun Content() {
        val resetPasswordViewModel = koinViewModel<ResetPasswordViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        GradientBackground {
            ResetPasswordScreen(
                resetPasswordState = resetPasswordViewModel.resetPasswordState,
                onBackPressed = {
                    navigator.pop()
                },
                onResetPasswordSuccess = { message ->
                    val notificationHandler = NotificationHandler(context)
                    notificationHandler.start(MainActivity::class.java, message)
                },
                onResetPasswordAction = resetPasswordViewModel::resetPasswordAction
            )
        }
    }
}