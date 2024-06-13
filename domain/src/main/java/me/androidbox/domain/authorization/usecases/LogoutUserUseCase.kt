package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel

fun interface LogoutUserUseCase {
    suspend fun execute(): CheckResult<Unit, DataError.Network, ErrorResponseModel>
}