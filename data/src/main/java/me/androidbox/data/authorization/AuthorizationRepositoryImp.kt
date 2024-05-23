package me.androidbox.data.authorization

import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.repository.APIResponse

class AuthorizationRepositoryImp(
    private val authorizationRemoteDataSource: AuthorizationRemoteDataSource
) : AuthorizationRepository {
    override suspend fun register(registerUserUserModel: RegisterUserModel): APIResponse<String> {
        return authorizationRemoteDataSource.registerUser(RegisterUserDto(
            user = UserDto("", "", "", ""),
            clientSecret = "",
            clientId = ""
        ))
    }
}