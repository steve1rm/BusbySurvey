package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.authorization.usecases.LogoutUserUseCase
import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.repository.AuthorizationRepository

class LogoutUserUseCaseImp(
    private val authorizationRepository: AuthorizationRepository
) : LogoutUserUseCase {
    override suspend fun execute(): APIResponse<Unit> {
        return authorizationRepository.logout()
    }
}
