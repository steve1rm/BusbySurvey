package me.androidbox.domain.authorization

import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.repository.APIResponse

interface AuthorizationRepository {
    suspend fun register(registerUserUserModel: RegisterUserModel): APIResponse<Unit>
    suspend fun login(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseModel>
}