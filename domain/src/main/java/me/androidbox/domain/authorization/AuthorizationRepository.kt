package me.androidbox.domain.authorization

import me.androidbox.domain.repository.APIResponse

interface AuthorizationRepository {
    fun register(): APIResponse<String>
}