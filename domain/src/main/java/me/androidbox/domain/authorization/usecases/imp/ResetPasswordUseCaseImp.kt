package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.ResetPasswordModel
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.domain.repository.AuthorizationRepository

class ResetPasswordUseCaseImp(
    private val authorizationRepository: AuthorizationRepository
) : ResetPasswordUseCase {
    override suspend fun execute(email: String): CheckResult<ResetPasswordModel, DataError.Network, ErrorResponseModel> {
        return authorizationRepository.resetPassword(email)
    }
}