package me.androidbox.data.authorization

import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.repository.APIResponse

class AuthorizationRepositoryImp : AuthorizationRepository {
    override fun register(registerUserUserModel: RegisterUserModel): APIResponse<String> {
        TODO()
    }
}