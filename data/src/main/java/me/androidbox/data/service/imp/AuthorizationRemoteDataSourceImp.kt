package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.authorization.UserDto
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.data.util.safeApiRequest
import me.androidbox.domain.repository.APIResponse

class AuthorizationRemoteDataSourceImp(
    private val httpClient: HttpClient
) : AuthorizationRemoteDataSource {
    override suspend fun registerUser(registerUserDto: RegisterUserDto): APIResponse<String> {

        return safeApiRequest {
            httpClient
                .post("https://survey-api.nimblehq.co/api/v1/registrations") {
                    setBody(RegisterUserDto(
                        UserDto("", "", "", ""),
                        clientId = "",
                        clientSecret = ""))
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
                .body<String>()
        }
    }
}