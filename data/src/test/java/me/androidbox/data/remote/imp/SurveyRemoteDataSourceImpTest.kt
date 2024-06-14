package me.androidbox.data.remote.imp

import com.google.common.truth.Truth.assertThat
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.remote.SurveyRemoteDataSource
import me.androidbox.data.survey.AttributesDto
import me.androidbox.data.survey.DataDto
import me.androidbox.data.survey.SurveyListDto
import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.repository.APIResponse
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import java.util.UUID

class SurveyRemoteDataSourceImpTest {

    private lateinit var surveyRemoteDataSource: SurveyRemoteDataSource
    private var authorizationLocalDataSource: AuthorizationLocalDataSource = mock(AuthorizationLocalDataSource::class.java)

    @Test
    fun `should fetch survey list`() = runTest {
        // Arrange
        val title = UUID.randomUUID().toString()
        val description = UUID.randomUUID().toString()
        val coverImageUrl = UUID.randomUUID().toString()

        whenever(authorizationLocalDataSource.get()).thenReturn(AuthorizationInfo(
            accessToken = UUID.randomUUID().toString(),
            refreshToken = UUID.randomUUID().toString()
        ))

        val surveyListDto = SurveyListDto(
            data = listOf(DataDto(
                AttributesDto(
                    "",
                    coverImageUrl,
                    "",
                    description,
                    "",
                    true,
                    "",
                    "",
                    "",
                    title
                ), "", "")))

        val surveyListDtoString =
            kotlinx.serialization.json.Json.encodeToString(surveyListDto)

        val client = createMockEngine(surveyListDtoString)
        surveyRemoteDataSource = SurveyRemoteDataSourceImp(client, authorizationLocalDataSource)

        // Act
        val actual = surveyRemoteDataSource.fetchSurveyList() as APIResponse.OnSuccess

        // Assert
        assertThat(surveyListDto).isEqualTo(actual.data)
    }

    private fun createMockEngine(content: String): HttpClient {
        val mockEngine = MockEngine {
            this.respond(
                content = content,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, Json.toString())
            )
        }

        return HttpClient(mockEngine) {
            install(ContentNegotiation) {
                this.json(
                    json = kotlinx.serialization.json.Json,
                    contentType = Json
                )
            }
        }
    }
}