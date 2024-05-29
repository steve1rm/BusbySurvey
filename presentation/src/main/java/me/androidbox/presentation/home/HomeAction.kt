package me.androidbox.presentation.home

import me.androidbox.domain.survey.models.SurveyListModel

interface HomeAction {
    data class FetchFromSplash(val surveyListModel: SurveyListModel) : HomeAction
    data object FetchFromNetwork : HomeAction
    data object LogoutUser : HomeAction
}