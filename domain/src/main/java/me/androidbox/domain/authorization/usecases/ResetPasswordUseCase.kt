package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.ResetPasswordModel

fun interface ResetPasswordUseCase {
    suspend fun execute(email: String): CheckResult<ResetPasswordModel, DataError.Network, ErrorResponseModel>
}