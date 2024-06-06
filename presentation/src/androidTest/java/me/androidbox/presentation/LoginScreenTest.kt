package me.androidbox.presentation

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import me.androidbox.presentation.authentication.login.LoginScreen
import me.androidbox.presentation.authentication.login.LoginState
import org.junit.Rule
import org.junit.Test
import java.util.UUID

class LoginScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun should_enter_email() {
        // Arrange
        rule.setContent {
            LoginScreen(
                loginState = LoginState(),
                onLoginSuccess = { },
                onForgotPassword = { },
                onLoginAction = { } )
        }

        // Act & Assert
        val input = UUID.randomUUID().toString()

        rule.onNodeWithText("Email").assertExists()
        rule.onNodeWithTag("email").performTextInput(input)
        rule.onNodeWithText(input).isDisplayed()
        rule.onNodeWithText("Login").performClick()
    }

    @Test
    fun should_enter_password() {
        rule.setContent {
            LoginScreen(
                loginState = LoginState(),
                onLoginSuccess = { },
                onForgotPassword = { },
                onLoginAction = { } )
        }

        // Act & Assert
        val input = UUID.randomUUID().toString()

        rule.onNodeWithText("Password").assertExists()
        rule.onNodeWithTag("password").performTextInput(input)
        rule.onNodeWithText(input).isDisplayed()
        rule.onNodeWithText("Login").performClick()
    }
}