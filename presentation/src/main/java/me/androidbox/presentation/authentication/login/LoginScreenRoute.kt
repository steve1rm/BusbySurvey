package me.androidbox.presentation.authentication.login

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun LoginScreenRoot() {

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

