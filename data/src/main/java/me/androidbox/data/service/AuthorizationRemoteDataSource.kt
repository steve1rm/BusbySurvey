package me.androidbox.data.service

import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.domain.repository.APIResponse

interface AuthorizationRemoteDataSource {
    suspend fun registerUser(registerUserDto: RegisterUserDto): APIResponse<Unit>
    suspend fun loginUser(): APIResponse<LoginResponseDto>
}