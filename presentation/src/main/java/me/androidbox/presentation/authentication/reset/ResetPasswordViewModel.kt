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
            resetPasswordState = resetPasswordState.copy(
                isLoading = true
            )

            /* Make request to reset password */
            val resetPasswordResult = resetPasswordUseCase
                .execute(resetPasswordState.email.text.toString().trim())

            when(resetPasswordResult) {
                is APIResponse.OnSuccess -> {
                    resetPasswordState = resetPasswordState.copy(
                        isResetPasswordSuccess = true,
                        message = resetPasswordResult.data.meta.message)
                }
                is APIResponse.OnFailure -> {
                    resetPasswordState = resetPasswordState.copy(
                        isResetPasswordSuccess = false)
                }
            }

            resetPasswordState = resetPasswordState.copy(
                isLoading = false
            )
        }
    }
}