package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.authorization.usecases.SetTokenAuthorizationUseCase
import me.androidbox.domain.repository.AuthorizationRepository

class SetTokenAuthorizationUseCaseImp(
    private val authorizationRepository: AuthorizationRepository
) : SetTokenAuthorizationUseCase {
    override suspend fun execute(authorizationInfo: AuthorizationInfo?) {
        return authorizationRepository.setTokenAuthorization(authorizationInfo)
    }
}