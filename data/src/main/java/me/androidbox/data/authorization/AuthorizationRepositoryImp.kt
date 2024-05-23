package me.androidbox.data.authorization

import me.androidbox.data.mappers.toLoginResponseModel
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.repository.APIResponse

class AuthorizationRepositoryImp(
    private val authorizationRemoteDataSource: AuthorizationRemoteDataSource
) : AuthorizationRepository {
    override suspend fun register(registerUserUserModel: RegisterUserModel): APIResponse<Unit> {
        return authorizationRemoteDataSource.registerUser(RegisterUserDto(
            user = UserDto("", "", "", ""),
            clientSecret = "",
            clientId = ""
        ))
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
}