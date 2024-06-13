package me.androidbox.domain.repository

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.models.ResetPasswordModel

interface AuthorizationRepository {
    suspend fun register(registerUserUserModel: RegisterUserModel): CheckResult<Unit, DataError.Network, ErrorResponseModel>
    suspend fun login(loginRequestModel: LoginRequestModel): CheckResult<LoginResponseModel, DataError.Network, ErrorResponseModel>
    suspend fun resetPassword(email: String): CheckResult<ResetPasswordModel, DataError.Network, ErrorResponseModel>
    suspend fun fetchTokenAuthorization(): AuthorizationInfo?
    suspend fun setTokenAuthorization(authorizationInfo: AuthorizationInfo?)
    suspend fun logout(): CheckResult<Unit, DataError.Network, ErrorResponseModel>
}