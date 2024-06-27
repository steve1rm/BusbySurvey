package me.androidbox.presentation.authentication.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import me.androidbox.presentation.ui.UiText

data class LoginState @OptIn(ExperimentalFoundationApi::class) constructor(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val isValidEmail: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val errorMessage: UiText = UiText.Empty,
    val showNotificationRationale: Boolean = false
)
