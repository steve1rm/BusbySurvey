package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.usecases.RegisterUsseCase

class RegisterUseCaseImp(private val authorizationRepository: AuthorizationRepository): RegisterUsseCase {
    override suspend fun execute() {
        authorizationRepository.register()
    }
}