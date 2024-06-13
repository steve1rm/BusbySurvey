package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.androidbox.data.BuildConfig
import me.androidbox.data.authorization.LoginRequestDto
import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.authorization.ResetPasswordDto
import me.androidbox.data.authorization.ResetPasswordRequestDto
import me.androidbox.data.authorization.UserDto
import me.androidbox.data.authorization.UserPasswordRequestDto
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.models.ErrorResponseDto
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.data.util.safeApiRequest
import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.LoginRequestModel

class AuthorizationRemoteDataSourceImp(
    private val httpClient: HttpClient,
    private val authorizationLocalDataSource: AuthorizationLocalDataSource
) : AuthorizationRemoteDataSource {
    override suspend fun registerUser(registerUserDto: RegisterUserDto): CheckResult<Unit, DataError.Network, ErrorResponseDto> {
        val safeResult = safeApiRequest<Unit> {
            val response =httpClient
                .post("https://survey-api.nimblehq.co/api/v1/registrations") {
                    setBody(RegisterUserDto(
                        user = UserDto(
                            email = registerUserDto.user.email,
                            registerUserDto.user.name,
                            registerUserDto.user.password,
                            registerUserDto.user.passwordConfirmation
                        ),
                        clientId = BuildConfig.CLIENT_KEY,
                        clientSecret = BuildConfig.CLIENT_SECRET))
                }
            response
        }

        return safeResult
    }

    override suspend fun loginUser(loginRequestModel: LoginRequestModel): CheckResult<LoginResponseDto, DataError.Network, ErrorResponseDto> {

        val safeResult = safeApiRequest<LoginResponseDto> {
            val response = httpClient
                .post("https://survey-api.nimblehq.co/api/v1/oauth/token") {
                    contentType(ContentType.Application.Json)

                    setBody(LoginRequestDto(
                        grantType = "password",
                        email = loginRequestModel.email,
                        password =  loginRequestModel.password,
                        clientId = BuildConfig.CLIENT_KEY,
                        clientSecret = BuildConfig.CLIENT_SECRET))
                }

            response
        }

        return safeResult
    }

    override suspend fun resetPassword(email: String): CheckResult<ResetPasswordDto, DataError.Network, ErrorResponseDto> {
        val safeResult = safeApiRequest<ResetPasswordDto> {
            val response = httpClient
                .post("https://survey-api.nimblehq.co/api/v1/passwords") {
                    setBody(
                        ResetPasswordRequestDto(
                            user = UserPasswordRequestDto(
                                email = email
                            ),
                            clientId = BuildConfig.CLIENT_KEY,
                            clientSecret = BuildConfig.CLIENT_SECRET
                        )
                    )
                }
            response
        }

        return safeResult
    }

    override suspend fun logoutUser(): CheckResult<Unit, DataError.Network, ErrorResponseDto> {
        val safeResult = safeApiRequest<Unit> {
            val requestBody = RequestBody(
                accessToken = authorizationLocalDataSource.get()?.accessToken ?: "",
                clientId = BuildConfig.CLIENT_KEY,
                clientSecret = BuildConfig.CLIENT_SECRET)

            val response = httpClient
                .post("https://survey-api.nimblehq.co/api/v1/oauth/revoke") {
                    setBody(requestBody)
                }

            response
        }

        return safeResult
    }

    @Serializable
    data class RequestBody(
        @SerialName("access_token")
        val accessToken: String,
        @SerialName("client_id")
        val clientId: String,
        @SerialName("client_secret")
        val clientSecret: String
    )
}