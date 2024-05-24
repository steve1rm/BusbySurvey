package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.repository.APIResponse

fun interface LoginUseCase {
    suspend fun execute(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseModel>
}