package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.authorization.models.ResetPasswordModel
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.repository.AuthorizationRepository

class ResetPasswordUseCaseImp(
    private val authorizationRepository: AuthorizationRepository
) : ResetPasswordUseCase {
    override suspend fun execute(email: String): APIResponse<ResetPasswordModel> {
        return authorizationRepository.resetPassword(email)
    }
}