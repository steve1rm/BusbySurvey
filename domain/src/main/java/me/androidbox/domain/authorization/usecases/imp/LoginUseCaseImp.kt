package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.usecases.LoginUseCase
import me.androidbox.domain.repository.APIResponse

class LoginUseCaseImp(private val authorizationRepository: AuthorizationRepository) : LoginUseCase {
    override suspend fun execute(loginRequestModel: LoginRequestModel): APIResponse<LoginResponseModel> {
         return authorizationRepository.login(loginRequestModel)
    }
}