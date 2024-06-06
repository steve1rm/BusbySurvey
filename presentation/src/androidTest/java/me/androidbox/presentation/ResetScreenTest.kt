package me.androidbox.presentation

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import me.androidbox.presentation.authentication.reset.ResetPasswordScreen
import me.androidbox.presentation.authentication.reset.ResetPasswordState
import org.junit.Rule
import org.junit.Test
import java.util.UUID

class ResetScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun should_enter_password() {
        rule.setContent {
            ResetPasswordScreen(
                resetPasswordState = ResetPasswordState(),
                onResetPasswordAction = { },
                onBackPressed = { },
                onResetPasswordSuccess = { })
        }
        val input = UUID.randomUUID().toString()

        rule.onNodeWithText("Email").assertExists()
        rule.onNodeWithTag("email").performTextInput(input)
        rule.onNodeWithText(input).isDisplayed()
        rule.onNodeWithText("Reset").performClick()
    }
}