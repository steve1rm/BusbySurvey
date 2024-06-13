package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.models.ResetPasswordModel

fun interface RegisterUseCase {
    suspend fun execute(registerUserModel: RegisterUserModel): CheckResult<Unit, DataError.Network, ErrorResponseModel>
}