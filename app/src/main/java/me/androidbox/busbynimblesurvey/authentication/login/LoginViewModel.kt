@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.busbynimblesurvey.authentication.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    fun LoginAction(loginAction: LoginAction) {
        when(loginAction) {
            LoginAction.OnForgotPassword -> {

            }
            LoginAction.OnLoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            loginState = loginState.copy(
                isLoggingIn = true)

            /* Make request to login */

            loginState = loginState.copy(
                isLoggingIn = true
            )

        }
    }
}