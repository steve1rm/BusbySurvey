package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase

class FetchTokenAuthorizationUseCaseImp(
    private val authorizationRepository: AuthorizationRepository
) : FetchTokenAuthorizationUseCase {
    override suspend fun execute(): AuthorizationInfo? {
        return authorizationRepository.fetchTokenAuthorization()
    }
}