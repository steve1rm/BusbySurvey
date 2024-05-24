package me.androidbox.presentation.authentication.login

sealed interface LoginEvent {
    data object OnLoginSuccess : LoginEvent
    data class OnLoginFailure(val error: String) : LoginEvent
}