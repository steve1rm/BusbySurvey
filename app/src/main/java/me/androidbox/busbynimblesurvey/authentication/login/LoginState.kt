package me.androidbox.busbynimblesurvey.authentication.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState

data class LoginState @OptIn(ExperimentalFoundationApi::class) constructor(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val isValidEmail: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false
)
