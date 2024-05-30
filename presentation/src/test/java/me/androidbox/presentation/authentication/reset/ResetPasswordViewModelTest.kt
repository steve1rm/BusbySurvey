package me.androidbox.presentation.authentication.reset

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.androidbox.domain.authorization.models.ResetPasswordModel
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.domain.repository.APIResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.util.UUID


class ResetPasswordViewModelTest {
    private val resetPasswordUseCase: ResetPasswordUseCase = mock(ResetPasswordUseCase::class.java)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `should reset password on success`() = runTest {
        // Arrange
        val message = UUID.randomUUID().toString()
        val resetPasswordViewModel = ResetPasswordViewModel(resetPasswordUseCase)

        whenever(resetPasswordUseCase.execute())
            .thenReturn(APIResponse.OnSuccess(ResetPasswordModel(
                me.androidbox.domain.authorization.models.MetaModel(
                    message = message
                )
            )))

        // Act
        resetPasswordViewModel.resetPasswordAction(ResetPasswordAction.OnPasswordResetClicked)

        // Assert
        assertThat(resetPasswordViewModel.resetPasswordState.message).isEqualTo(message)
    }
}