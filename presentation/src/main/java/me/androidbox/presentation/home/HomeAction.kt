package me.androidbox.presentation.home

sealed interface HomeAction {
    data object LogoutUser : HomeAction
    data object CancelLogout : HomeAction
    data object ContinueLogout : HomeAction
    data class SubmitNotificationPermissionInfo(
        val acceptedNotificationPermission: Boolean,
        val showNotificationPermissionRationale: Boolean
    ) : HomeAction
    data object DismissRationaleDialog : HomeAction
}
