package me.androidbox.busbynimblesurvey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import timber.log.Timber

class MainViewModel(
    private val fetchTokenAuthorizationUseCase: FetchTokenAuthorizationUseCase
) : ViewModel() {

    var mainState by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            mainState = mainState.copy(
                isCheckingAuthorization = true)

            val result = fetchTokenAuthorizationUseCase.execute()
            Timber.d("MainViewModel $result")
            mainState = mainState.copy(
                isLoggedIn = fetchTokenAuthorizationUseCase.execute() != null)

            mainState = mainState.copy(isCheckingAuthorization = false)
        }
    }
}