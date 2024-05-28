package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.authorization.AuthorizationInfo

fun interface FetchTokenAuthorizationUseCase {
    suspend fun execute(): AuthorizationInfo?
}