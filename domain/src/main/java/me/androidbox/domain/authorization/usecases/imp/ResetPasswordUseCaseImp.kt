package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.repository.AuthorizationRepository
import me.androidbox.domain.authorization.models.ResetPasswordModel
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.domain.repository.APIResponse

class ResetPasswordUseCaseImp(
    private val authorizationRepository: AuthorizationRepository
) : ResetPasswordUseCase {
    override suspend fun execute(): APIResponse<ResetPasswordModel> {
        return authorizationRepository.resetPassword()
    }
}