package me.androidbox.domain.repository

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.survey.models.SurveyListModel

interface SurveyRepository {
    suspend fun fetchSurveyList(): CheckResult<SurveyListModel, DataError.Network, ErrorResponseModel>
    suspend fun fetchSurveyDetails(): APIResponse<SurveyListModel>
}