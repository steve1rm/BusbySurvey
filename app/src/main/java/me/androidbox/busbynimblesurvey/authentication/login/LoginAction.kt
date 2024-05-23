package me.androidbox.busbynimblesurvey.authentication.login

sealed interface LoginAction {
    data object OnLoginClicked : LoginAction
    data object OnForgotPassword : LoginAction
}
