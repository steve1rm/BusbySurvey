@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.presentation.authentication.reset

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.domain.repository.APIResponse

class ResetPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    var resetPasswordState by mutableStateOf(ResetPasswordState())
        private set

    fun resetPasswordAction(resetPasswordAction: ResetPasswordAction) {
        when(resetPasswordAction) {
            ResetPasswordAction.OnPasswordResetClicked -> {
                resetPassword()
            }
        }
    }

    private fun resetPassword() {
        viewModelScope.launch {

            /* Make request to login */
            val resetPasswordResult = resetPasswordUseCase.execute()

            when(resetPasswordResult) {
                is APIResponse.OnSuccess -> {
                    resetPasswordState = resetPasswordState.copy(
                        isResetPasswordSuccess = true)
                }
                is APIResponse.OnFailure -> {
                    resetPasswordState = resetPasswordState.copy(
                        isResetPasswordSuccess = false)
                }
            }
        }
    }
}