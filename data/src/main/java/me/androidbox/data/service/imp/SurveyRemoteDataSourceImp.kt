package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.models.ErrorResponseDto
import me.androidbox.data.service.SurveyRemoteDataSource
import me.androidbox.data.survey.SurveyListDto
import me.androidbox.data.util.safeApiRequest
import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.repository.APIResponse

class SurveyRemoteDataSourceImp(
    private val httpClient: HttpClient,
    private val authorizationLocalDataSource: AuthorizationLocalDataSource
) : SurveyRemoteDataSource {

    override suspend fun fetchSurveyList(): CheckResult<SurveyListDto, DataError.Network, ErrorResponseDto> {
        val accessToken = authorizationLocalDataSource.get()?.accessToken ?: ""

       val safeResult = safeApiRequest<SurveyListDto> {
            val response = httpClient
                .get(urlString = "https://survey-api.nimblehq.co/api/v1/surveys?page[number]=1&page[size]=5") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $accessToken")
                    }
                }

           response
        }

        return safeResult
    }

    override suspend fun fetchSurveyDetails(): APIResponse<SurveyListDto> {
        TODO("Not yet implemented")
    }
}