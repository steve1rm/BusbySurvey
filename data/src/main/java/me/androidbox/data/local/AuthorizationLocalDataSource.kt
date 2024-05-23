package me.androidbox.data.local

import me.androidbox.domain.authorization.AuthorizationInfo

interface AuthorizationLocalDataSource {
    suspend fun get(): AuthorizationInfo?
    suspend fun set(authorizationInfo: AuthorizationInfo?)
}