package me.androidbox.presentation.home

interface HomeAction {
    data object LogoutUser : HomeAction
    data object CancelLogout : HomeAction
    data object ContinueLogout : HomeAction
}