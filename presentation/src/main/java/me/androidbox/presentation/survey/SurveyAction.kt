package me.androidbox.presentation.survey

sealed interface SurveyAction {
    data object OnSurveyClicked : SurveyAction
}
