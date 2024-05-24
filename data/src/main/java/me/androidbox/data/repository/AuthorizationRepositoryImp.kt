package me.androidbox.data.repository

import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.authorization.UserDto
import me.androidbox.data.mappers.toLoginResponseModel
import me.androidbox.data.mappers.toResetPasswordModel
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.models.ResetPasswordModel
import me.androidbox.domain.repository.APIResponse

class AuthorizationRepositoryImp(
    private val authorizationRemoteDataSource: AuthorizationRemoteDataSource
) : AuthorizationRepository {
    override suspend fun register(registerUserUserModel: RegisterUserModel): APIResponse<Unit> {
        return authorizationRemoteDataSource.registerUser(
            RegisterUserDto(
            user = UserDto("", "", "", ""),
            clientSecret = "",
            clientId = ""
        )
        )
    }

    override suspend fun login(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseModel> {
        return when(val apiResponse = authorizationRemoteDataSource.loginUser(loginRequestModel)) {
            is APIResponse.OnSuccess -> {
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

    override suspend fun resetPassword(): APIResponse<ResetPasswordModel> {
        return when(val apiResponse = authorizationRemoteDataSource.resetPassword()) {
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
}