package me.androidbox.data.mappers

import me.androidbox.data.models.AuthorizationInfoSerializable
import me.androidbox.domain.authorization.AuthorizationInfo

fun AuthorizationInfo.toAuthorizationSerializable(): AuthorizationInfoSerializable {
    return AuthorizationInfoSerializable(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        userId = this.userId
    )
}

fun AuthorizationInfoSerializable.toAuthorizationInfo(): AuthorizationInfo {
    return AuthorizationInfo(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        userId = this.userId
    )
}