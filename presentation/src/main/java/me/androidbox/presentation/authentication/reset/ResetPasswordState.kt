@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.presentation.authentication.reset

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState

data class ResetPasswordState(
    val email: TextFieldState = TextFieldState(),
    val isResetPasswordSuccess: Boolean = false,
    val message: String = "",
    val isLoading: Boolean = false
)
