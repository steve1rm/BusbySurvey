@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.presentation.authentication.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.domain.CheckResult
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.usecases.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var loginState by mutableStateOf(LoginState())
        private set

    fun loginAction(loginAction: LoginAction) {
        when(loginAction) {
            LoginAction.OnLoginClicked -> {
                login()
            }
            LoginAction.OnResetScreen -> {
                loginState = loginState.copy(
                    isLoginSuccess = false,
                    canLogin = false,
                    errorMessage = "",
                    isLoggingIn = false
                )
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            loginState = loginState.copy(
                isLoggingIn = true)

            /* Make request to login */
            val loginResult = loginUseCase.execute(
                LoginRequestModel(
                    email = loginState.email.text.toString().trim(),
                    password = loginState.password.text.toString())
            )

            when(loginResult) {
                is CheckResult.Success-> {
                    loginState = loginState.copy(
                        isLoginSuccess = true,
                        isLoggingIn = false
                    )
                }
                is CheckResult.Failure -> {
                    loginState = loginState.copy(
                        isLoginSuccess = false,
                        errorMessage = loginResult.responseError?.errors?.first()?.detail ?: "",
                        isLoggingIn = false
                    )
                }
            }
        }
    }
}