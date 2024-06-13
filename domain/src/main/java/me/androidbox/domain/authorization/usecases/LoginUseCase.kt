package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel

fun interface LoginUseCase {
    suspend fun execute(loginRequestModel: LoginRequestModel): CheckResult<LoginResponseModel, DataError.Network, ErrorResponseModel>
}