package me.androidbox.domain.repository

import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.models.ResetPasswordModel

interface AuthorizationRepository {
    suspend fun register(registerUserUserModel: RegisterUserModel): APIResponse<Unit>
    suspend fun login(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseModel>
    suspend fun resetPassword(): APIResponse<ResetPasswordModel>
    suspend fun fetchTokenAuthorization(): AuthorizationInfo?
}