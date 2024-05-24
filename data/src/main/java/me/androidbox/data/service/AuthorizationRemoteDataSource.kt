package me.androidbox.data.service

import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.repository.APIResponse

interface AuthorizationRemoteDataSource {
    suspend fun registerUser(registerUserDto: RegisterUserDto): APIResponse<Unit>
    suspend fun loginUser(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseDto>
}