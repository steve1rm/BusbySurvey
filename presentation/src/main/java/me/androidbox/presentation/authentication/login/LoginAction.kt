package me.androidbox.presentation.authentication.login

sealed interface LoginAction {
    data object OnLoginClicked : LoginAction
    data object OnResetScreen : LoginAction
    data class SubmitNotificationPermissionInfo(
        val acceptedNotificationPermission: Boolean,
        val showNotificationPermissionRationale: Boolean
    ) : LoginAction
    data object DismissRationaleDialog : LoginAction

}
