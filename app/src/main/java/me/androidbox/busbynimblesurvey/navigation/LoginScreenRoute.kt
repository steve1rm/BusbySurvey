package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.presentation.authentication.login.LoginScreen
import me.androidbox.presentation.authentication.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber


data object LoginScreenRoute : Screen {

    @Composable
    override fun Content() {
        val loginViewModel = koinViewModel<LoginViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        LoginScreen(
            loginState = loginViewModel.loginState,
            onLoginAction = loginViewModel::loginAction,
            onLoginSuccess = {
                /** Navigate to the home screen */
                Timber.d("onLoginSuccess")
                navigator.replace(HomeScreenRoute)
            },
            onForgotPassword = {
                /** Navigate to the home screen */
                Timber.d("onForgotPassword")
                navigator.push(ResetScreenRoute)
            }
        )
    }
}
