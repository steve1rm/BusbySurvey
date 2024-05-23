package me.androidbox.presentation.authentication.login

sealed interface LoginAction {
    data object OnLoginClicked : LoginAction
    data object OnForgotPassword : LoginAction
}
