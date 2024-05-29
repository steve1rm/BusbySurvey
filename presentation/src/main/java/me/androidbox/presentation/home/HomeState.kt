package me.androidbox.presentation.home

data class HomeState(
    val homeItems: List<HomeItems> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccessLogout: Boolean = false
)


