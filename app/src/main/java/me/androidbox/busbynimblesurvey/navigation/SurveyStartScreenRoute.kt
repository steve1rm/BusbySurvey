package me.androidbox.busbynimblesurvey.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.presentation.survey.SurveyViewModel
import org.koin.androidx.compose.koinViewModel

object SurveyStartScreenRoute : Screen {

    @Composable
    override fun Content() {
        val surveyViewModel = koinViewModel<SurveyViewModel>()


    }
}