package me.androidbox.domain.authorization.usecases

import me.androidbox.domain.authorization.AuthorizationInfo

fun interface SetTokenAuthorizationUseCase {
    suspend fun execute(authorizationInfo: AuthorizationInfo?)
}