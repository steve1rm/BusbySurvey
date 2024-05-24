package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import me.androidbox.data.BuildConfig
import me.androidbox.data.authorization.LoginRequestDto
import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.authorization.UserDto
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.data.util.safeApiRequest
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.repository.APIResponse

class AuthorizationRemoteDataSourceImp(
    private val httpClient: HttpClient
) : AuthorizationRemoteDataSource {
    override suspend fun registerUser(registerUserDto: RegisterUserDto): APIResponse<Unit> {

        return safeApiRequest {
            httpClient
                .post("https://survey-api.nimblehq.co/api/v1/registrations") {
                    setBody(RegisterUserDto(
                        UserDto(
                            email = "test6@mail.com", "steve", "Test12345", "Test12345"),
                        clientId = BuildConfig.CLIENT_KEY,
                        clientSecret = BuildConfig.CLIENT_SECRET))
                }
                .body<Unit>()
        }
    }

    override suspend fun loginUser(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseDto> {
        return safeApiRequest {
            httpClient
                .post("https://survey-api.nimblehq.co/api/v1/oauth/token") {
                    setBody(LoginRequestDto(
                        grantType = "password",
                        email = "test6@mail.com",
                        password =  "Test12345",
                        clientId = BuildConfig.CLIENT_KEY,
                        clientSecret = BuildConfig.CLIENT_SECRET))
                }
                .body<LoginResponseDto>()
        }
    }
}