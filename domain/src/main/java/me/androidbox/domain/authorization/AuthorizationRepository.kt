package me.androidbox.domain.authorization

import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.repository.APIResponse

interface AuthorizationRepository {
    fun register(registerUserUserModel: RegisterUserModel): APIResponse<String>
}