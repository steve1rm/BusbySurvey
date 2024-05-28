package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.repository.AuthorizationRepository
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.usecases.RegisterUseCase
import me.androidbox.domain.repository.APIResponse

class RegisterUseCaseImp(private val authorizationRepository: AuthorizationRepository): RegisterUseCase {
    override suspend fun execute(registerUserModel: RegisterUserModel): APIResponse<Unit> {
        return authorizationRepository.register(registerUserModel)
    }
}