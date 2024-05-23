package me.androidbox.domain.authorization

import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.repository.APIResponse

interface AuthorizationRepository {
    suspend fun register(registerUserUserModel: RegisterUserModel): APIResponse<Unit>
}