package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.repository.APIResponse
import timber.log.Timber

class AuthorizationRemoteDataSourceImp(
    private val httpClient: HttpClient,
    private val authorizationLocalDataSource: AuthorizationLocalDataSource
) : AuthorizationRemoteDataSource {
    override suspend fun registerUser(registerUserDto: RegisterUserDto): APIResponse<Unit> {

      /*  return safeApiRequest {
            httpClient
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
                .body<Unit>()
        }
*/
        TODO()
    }

    override suspend fun loginUser(loginRequestModel: LoginRequestModel): CheckResult<LoginResponseDto, DataError.Network, ErrorResponseDto> {

        val safeResult = safeApiRequest<LoginResponseDto, DataError.Network> {
            val clientResult = httpClient
                .post("https://survey-api.nimblehq.co/api/v1/oauth/token") {
                    contentType(ContentType.Application.Json)

                    setBody(LoginRequestDto(
                        grantType = "password",
                        email = loginRequestModel.email,
                        password =  loginRequestModel.password,
                        clientId = BuildConfig.CLIENT_KEY,
                        clientSecret = BuildConfig.CLIENT_SECRET))
                }

            return if(clientResult.status.value == 200) {
                CheckResult.Success(clientResult.body<LoginResponseDto>())
            }
            else {
                val result = clientResult.body<ErrorResponseDto>()
                Timber.d("Result ${result.errors}")
                CheckResult.Failure(responseError = clientResult.body<ErrorResponseDto>())
            }
        }

        return safeResult
    }

    override suspend fun resetPassword(email: String): APIResponse<ResetPasswordDto> {
/*
        return safeApiRequest {
            httpClient
                .post("https://survey-api.nimblehq.co/api/v1/passwords") {
                    setBody(ResetPasswordRequestDto(
                        user = UserPasswordRequestDto(
                            email = email
                        ),
                        clientId = BuildConfig.CLIENT_KEY,
                        clientSecret = BuildConfig.CLIENT_SECRET
                    ))
                }
                .body<ResetPasswordDto>()
        }
*/

        TODO()
    }

    override suspend fun logoutUser(): APIResponse<Unit> {
/*
        return safeApiRequest {
            val requestBody = RequestBody(
                accessToken = authorizationLocalDataSource.get()?.accessToken ?: "",
                clientId = BuildConfig.CLIENT_KEY,
                clientSecret = BuildConfig.CLIENT_SECRET)

            httpClient
                .post("https://survey-api.nimblehq.co/api/v1/oauth/revoke") {
                    setBody(requestBody)
                }
                .body<Unit>()
        }
*/

        TODO()
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