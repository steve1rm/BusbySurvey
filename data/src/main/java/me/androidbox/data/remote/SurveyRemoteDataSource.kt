package me.androidbox.data.remote

import me.androidbox.data.models.ErrorResponseDto
import me.androidbox.data.survey.SurveyListDto
import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.repository.APIResponse

interface SurveyRemoteDataSource {
    suspend fun fetchSurveyList(): CheckResult<SurveyListDto, DataError.Network, ErrorResponseDto>
    suspend fun fetchSurveyDetails(): APIResponse<SurveyListDto>
}