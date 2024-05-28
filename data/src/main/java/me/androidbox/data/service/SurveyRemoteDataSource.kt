package me.androidbox.data.service

import me.androidbox.data.survey.SurveyListDto
import me.androidbox.domain.repository.APIResponse

interface SurveyRemoteDataSource {
    suspend fun fetchSurveyList(): APIResponse<SurveyListDto>
    suspend fun fetchSurveyDetails(): APIResponse<SurveyListDto>
}