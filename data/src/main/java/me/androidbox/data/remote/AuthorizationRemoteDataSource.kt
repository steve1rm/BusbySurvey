package me.androidbox.data.remote

import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.authorization.ResetPasswordDto
import me.androidbox.data.models.ErrorResponseDto
import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.LoginRequestModel

interface AuthorizationRemoteDataSource {
    suspend fun registerUser(registerUserDto: RegisterUserDto): CheckResult<Unit, DataError.Network, ErrorResponseDto>
    suspend fun loginUser(loginRequestModel: LoginRequestModel): CheckResult<LoginResponseDto, DataError.Network, ErrorResponseDto>
    suspend fun resetPassword(email: String): CheckResult<ResetPasswordDto, DataError.Network, ErrorResponseDto>
    suspend fun logoutUser(): CheckResult<Unit, DataError.Network, ErrorResponseDto>
    suspend fun clearClientToken()
}