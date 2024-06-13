package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.usecases.LogoutUserUseCase
import me.androidbox.domain.repository.AuthorizationRepository

class LogoutUserUseCaseImp(
    private val authorizationRepository: AuthorizationRepository
) : LogoutUserUseCase {
    override suspend fun execute(): CheckResult<Unit, DataError.Network, ErrorResponseModel> {
        return authorizationRepository.logout()
    }
}
