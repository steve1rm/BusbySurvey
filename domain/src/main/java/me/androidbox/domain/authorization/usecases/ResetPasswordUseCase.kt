package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.authorization.models.ResetPasswordModel
import me.androidbox.domain.repository.APIResponse

fun interface ResetPasswordUseCase {
    suspend fun execute(): APIResponse<ResetPasswordModel>
}