package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import me.androidbox.data.authorization.AttributesDto
import me.androidbox.data.authorization.LoginRequestDto
import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.repository.APIResponse
import org.junit.Assert
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class AuthorizationRemoteDataSourceImpTest {

    private lateinit var authorizationRemoteDataSource: AuthorizationRemoteDataSource

    @Test
    fun `should login user`() = runTest {
        // Arrange
        val loginResponseDto = LoginResponseDto(
            data = me.androidbox.data.authorization.DataDto(
                attributes = AttributesDto(
                    createdAt = Random.nextInt(),
                    expiresIn = Random.nextInt(),
                    accessToken = UUID.randomUUID().toString(),
                    refreshToken = UUID.randomUUID().toString(),
                    tokenType = UUID.randomUUID().toString()
                ),
                id = UUID.randomUUID().toString(),
                type = UUID.randomUUID().toString(),
            )
        )

        val loginResponseString =
            kotlinx.serialization.json.Json.encodeToString(loginResponseDto)

        val client = createMockEngine(loginResponseString)
        authorizationRemoteDataSource = AuthorizationRemoteDataSourceImp(client)

       val loginRequestDto = LoginRequestDto(
            grantType = "password",
            email = UUID.randomUUID().toString(),
            password =  UUID.randomUUID().toString(),
            clientId = UUID.randomUUID().toString(),
            clientSecret = UUID.randomUUID().toString())


        // Act
        val actual = authorizationRemoteDataSource.loginUser(loginRequestDto) as APIResponse.OnSuccess

        // Assert
        Assert.assertEquals(loginRequestDto, actual.data)
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
                    contentType = ContentType.Any
                )

            }
        }
    }
}

/*
Fail to prepare request body for sending.
The body type is: class me.androidbox.data.authorization.LoginRequestDto, with Content-Type: null.

If you expect serialized body, please check that you have installed the corresponding plugin(like `ContentNegotiation`) and set `Content-Type` header.
* */
