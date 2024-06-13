package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.usecases.LoginUseCase
import me.androidbox.domain.repository.AuthorizationRepository

class LoginUseCaseImp(private val authorizationRepository: AuthorizationRepository) : LoginUseCase {
    override suspend fun execute(loginRequestModel: LoginRequestModel): CheckResult<LoginResponseModel, DataError.Network, ErrorResponseModel> {
        return authorizationRepository.login(loginRequestModel)
    }
}
