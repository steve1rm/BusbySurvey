package me.androidbox.presentation.authentication.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber


object LoginScreenRoot : Screen {

    @Composable
    override fun Content() {
        val loginViewModel = koinViewModel<LoginViewModel>()

        LoginScreen(
            loginState = loginViewModel.loginState,
            onLoginAction = loginViewModel::loginAction,
            onLoginSuccess = {
                /** Navigate to the home screen */
                Timber.d("onLoginSuccess")
            },
            onLoginFailure = {
                /** Display message */
                Timber.d("onLoginFailure")
            },
            onForgotPassword = {
                /** Navigate to the home screen */
                Timber.d("onForgotPassword")
            }
        )
    }
}

