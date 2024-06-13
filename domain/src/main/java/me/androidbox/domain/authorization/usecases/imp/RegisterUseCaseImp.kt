package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.usecases.RegisterUseCase
import me.androidbox.domain.repository.AuthorizationRepository

class RegisterUseCaseImp(private val authorizationRepository: AuthorizationRepository): RegisterUseCase {
    override suspend fun execute(registerUserModel: RegisterUserModel): CheckResult<Unit, DataError.Network, ErrorResponseModel> {
        return authorizationRepository.register(registerUserModel)
    }
}