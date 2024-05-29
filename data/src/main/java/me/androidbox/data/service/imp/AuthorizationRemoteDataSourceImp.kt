package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import me.androidbox.data.BuildConfig
import me.androidbox.data.authorization.LoginRequestDto
import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.authorization.ResetPasswordDto
import me.androidbox.data.authorization.ResetPasswordRequestDto
import me.androidbox.data.authorization.UserDto
import me.androidbox.data.authorization.UserPasswordRequestDto
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.data.util.safeApiRequest
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.repository.APIResponse

class AuthorizationRemoteDataSourceImp(
    private val httpClient: HttpClient,
    private val authorizationLocalDataSource: AuthorizationLocalDataSource
) : AuthorizationRemoteDataSource {
    override suspend fun registerUser(registerUserDto: RegisterUserDto): APIResponse<Unit> {

        return safeApiRequest {
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
    }

    override suspend fun loginUser(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseDto> {
        return safeApiRequest {
            httpClient
                .post("https://survey-api.nimblehq.co/api/v1/oauth/token") {
                    setBody(LoginRequestDto(
                        grantType = "password",
                        email = loginRequestModel.email,
                        password =  loginRequestModel.password,
                        clientId = BuildConfig.CLIENT_KEY,
                        clientSecret = BuildConfig.CLIENT_SECRET))
                }.body<LoginResponseDto>()
        }
    }

    override suspend fun resetPassword(): APIResponse<ResetPasswordDto> {
       return safeApiRequest {
           httpClient
               .post("https://survey-api.nimblehq.co/api/v1/passwords") {
                   setBody(ResetPasswordRequestDto(
                       user = UserPasswordRequestDto(
                           email = "test6@test.com"
                       ),
                       clientId = BuildConfig.CLIENT_KEY,
                       clientSecret = BuildConfig.CLIENT_SECRET
                   ))
               }
               .body<ResetPasswordDto>()
       }
    }

    override suspend fun logoutUser(): APIResponse<Unit> {
        return safeApiRequest {
            val requestBody = Parameters.build {
                append("access_token", authorizationLocalDataSource.get()?.accessToken ?: "")
                append("client_id", BuildConfig.CLIENT_KEY)
                append("client_secrert", BuildConfig.CLIENT_SECRET)
            }

            httpClient
                .post("https://survey-api.nimblehq.co/api/v1/oauth/revoke") {
                    setBody(FormDataContent(requestBody))
                }
                .body<Unit>()

        }
    }
}