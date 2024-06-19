package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import me.androidbox.presentation.survey.SurveyStartScreen
import me.androidbox.presentation.survey.SurveyViewModel
import org.koin.androidx.compose.koinViewModel

object SurveyStartScreenRoute : Screen {

    @Composable
    override fun Content() {
        val surveyViewModel = koinViewModel<SurveyViewModel>()
        val navigator = LocalNavigator.currentOrThrow

        SurveyStartScreen(
            onBackPressed = {
                navigator.pop()
            },
            onStartSurveyClicked = {
                navigator.replace(ThankYouScreenRoute)
            }
        )
    }
}