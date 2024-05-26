package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.service.SurveyRemoteDataSource
import me.androidbox.data.survey.SurveyListDto
import me.androidbox.data.util.safeApiRequest
import me.androidbox.domain.repository.APIResponse

class SurveyRemoteDataSourceImp(
    private val httpClient: HttpClient,
    private val authorizationLocalDataSource: AuthorizationLocalDataSource
) : SurveyRemoteDataSource {

    override suspend fun fetchSurveyList(): APIResponse<SurveyListDto> {
        val accessToken = authorizationLocalDataSource.get()?.accessToken ?: ""

        return safeApiRequest {
            httpClient
                .get(urlString = "https://survey-api.nimblehq.co/api/v1/surveys?page[number]=1&page[size]=5") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $accessToken")
                    }
                }
                .body<SurveyListDto>()
        }
    }

    override suspend fun fetchSurveyDetails(): APIResponse<SurveyListDto> {
        TODO("Not yet implemented")
    }
}