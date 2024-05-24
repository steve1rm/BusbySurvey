package me.androidbox.presentation.authentication.reset

sealed interface ResetPasswordAction {
    data object OnPasswordResetClicked : ResetPasswordAction
}
