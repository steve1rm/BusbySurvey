package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.repository.APIResponse

fun interface LogoutUserUseCase {
    suspend fun execute(): APIResponse<Unit>
}