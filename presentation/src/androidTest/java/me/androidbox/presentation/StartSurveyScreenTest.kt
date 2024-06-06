package me.androidbox.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import me.androidbox.presentation.survey.SurveyStartScreen
import org.junit.Rule
import org.junit.Test

class StartSurveyScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun should_show_survey_screen() {
        // Arrange
        rule.setContent {
            SurveyStartScreen(
                onBackPressed = {},
                onStartSurveyClicked = {})
        }

        // Act & Assert
        rule.onNodeWithText("Working from Check-In").assertExists()
        rule.onNodeWithText("We would like to know how you feel about our work from home (WFH) experience").assertExists()
        rule.onNodeWithText("Start Survey").performClick()
    }
}