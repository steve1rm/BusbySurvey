package me.androidbox.domain.repository

import me.androidbox.domain.survey.models.SurveyListModel

interface SurveyRepository {
    suspend fun fetchSurveyList(): APIResponse<SurveyListModel>
    suspend fun fetchSurveyDetails(): APIResponse<SurveyListModel>
}