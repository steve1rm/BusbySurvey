package me.androidbox.presentation.home

sealed interface HomeAction {
    data object LogoutUser : HomeAction
    data object CancelLogout : HomeAction
    data object ContinueLogout : HomeAction
}
