package me.androidbox.data.service.imp

import io.ktor.client.HttpClient
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.domain.repository.APIResponse

class AuthorizationRemoteDataSourceImp(
    private val httpClient: HttpClient
) : AuthorizationRemoteDataSource {
    override suspend fun registerUser(registerUserDto: RegisterUserDto): APIResponse<String> {

       TODO()
    }
}