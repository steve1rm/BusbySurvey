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


class LoginTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun shouldEnterEmail() {
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
}