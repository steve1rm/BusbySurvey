package me.androidbox.busbynimblesurvey

import me.androidbox.domain.survey.models.SurveyListModel

data class MainState(
    val isLoggedIn: Boolean = false,
    val isCheckingAuthorization: Boolean = false,
    val surveyListModel: SurveyListModel = SurveyListModel()
)
