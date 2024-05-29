package me.androidbox.data.repository

import me.androidbox.data.BuildConfig
import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.authorization.UserDto
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.mappers.toAuthorizationInfo
import me.androidbox.data.mappers.toLoginResponseModel
import me.androidbox.data.mappers.toResetPasswordModel
import me.androidbox.data.models.AuthorizationInfoSerializable
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.repository.AuthorizationRepository
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.models.ResetPasswordModel
import me.androidbox.domain.repository.APIResponse

class AuthorizationRepositoryImp(
    private val authorizationRemoteDataSource: AuthorizationRemoteDataSource,
    private val authorizationLocalDataSource: AuthorizationLocalDataSource
) : AuthorizationRepository {
    override suspend fun register(registerUserUserModel: RegisterUserModel): APIResponse<Unit> {
        return authorizationRemoteDataSource.registerUser(
            RegisterUserDto(
                user = UserDto(
                    email = registerUserUserModel.email,
                    name = registerUserUserModel.name,
                    password = registerUserUserModel.password,
                    passwordConfirmation = registerUserUserModel.passwordConfirmation
                ),
                clientSecret = BuildConfig.CLIENT_SECRET,
                clientId = BuildConfig.CLIENT_KEY
            )
        )
    }

    override suspend fun login(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseModel> {
        return when(val apiResponse = authorizationRemoteDataSource.loginUser(loginRequestModel)) {
            is APIResponse.OnSuccess -> {
                val authorizationInfoSerializable = AuthorizationInfoSerializable(
                    accessToken = apiResponse.data.data.attributes.accessToken,
                    refreshToken = apiResponse.data.data.attributes.refreshToken)

                authorizationLocalDataSource.set(authorizationInfoSerializable.toAuthorizationInfo())
                APIResponse.OnSuccess(apiResponse.data.toLoginResponseModel())
            }
            is APIResponse.OnFailure -> {
                APIResponse.OnFailure(apiResponse.error)
            }

            else -> {
                throw IllegalStateException("Something unknown happened")
            }
        }
    }

    override suspend fun resetPassword(email: String): APIResponse<ResetPasswordModel> {
        return when(val apiResponse = authorizationRemoteDataSource.resetPassword(email)) {
            is APIResponse.OnSuccess -> {
                APIResponse.OnSuccess(apiResponse.data.toResetPasswordModel())
            }
            is APIResponse.OnFailure -> {
                APIResponse.OnFailure(apiResponse.error)
            }

            else -> {
                throw IllegalStateException("Something unknown happened")
            }
        }
    }

    override suspend fun fetchTokenAuthorization(): AuthorizationInfo? {
        return authorizationLocalDataSource.get()
    }

    override suspend fun setTokenAuthorization(authorizationInfo: AuthorizationInfo?) {
        return authorizationLocalDataSource.set(authorizationInfo)
    }

    override suspend fun logout(): APIResponse<Unit> {
        return when(val apiResponse = authorizationRemoteDataSource.logoutUser()) {
            is APIResponse.OnSuccess -> {
                APIResponse.OnSuccess(Unit)
            }
            is APIResponse.OnFailure -> {
                APIResponse.OnFailure(apiResponse.error)
            }

            else -> {
                throw IllegalStateException("Something unknown happened")
            }
        }
    }
}