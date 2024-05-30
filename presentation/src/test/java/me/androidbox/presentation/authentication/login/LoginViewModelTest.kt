@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.presentation.authentication.login

import androidx.compose.foundation.ExperimentalFoundationApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.androidbox.domain.authorization.models.AttributesModel
import me.androidbox.domain.authorization.models.DataModel
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.usecases.LoginUseCase
import me.androidbox.domain.repository.APIResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.lang.Exception

class LoginViewModelTest {

    private val loginUseCase: LoginUseCase = mock(LoginUseCase::class.java)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `should login with success`() = runTest {
        // Arrange
        val loginViewModel = LoginViewModel(loginUseCase)

        whenever(loginUseCase.execute(LoginRequestModel(email = loginViewModel.loginState.email.text.toString(), password = loginViewModel.loginState.email.text.toString())))
            .thenReturn(APIResponse
                .OnSuccess(LoginResponseModel(DataModel(AttributesModel("", 0, 0, "", ""), "", ""))))

        // Act
        loginViewModel.loginAction(LoginAction.OnLoginClicked)

        // Assert
        assertThat(loginViewModel.loginState.isLoginSuccess).isTrue()
    }

    @Test
    fun `should login with failure`() = runTest {
        // Arrange
        val loginViewModel = LoginViewModel(loginUseCase)

        whenever(loginUseCase.execute(LoginRequestModel(email = loginViewModel.loginState.email.text.toString(), password = loginViewModel.loginState.email.text.toString())))
            .thenReturn(APIResponse
                .OnFailure(Exception()))

        // Act
        loginViewModel.loginAction(LoginAction.OnLoginClicked)

        // Assert
        assertThat(loginViewModel.loginState.isLoginSuccess).isFalse()
    }
}