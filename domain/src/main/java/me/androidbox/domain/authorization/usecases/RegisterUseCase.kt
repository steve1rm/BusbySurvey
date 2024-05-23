package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.repository.APIResponse

fun interface RegisterUseCase {
    suspend fun execute(registerUserModel: RegisterUserModel): APIResponse<String>
}