package me.androidbox.data.mappers

import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.models.AuthorizationInfoSerializable
import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.authorization.models.AttributesModel
import me.androidbox.domain.authorization.models.DataModel
import me.androidbox.domain.authorization.models.LoginResponseModel

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

fun LoginResponseDto.toLoginResponseModel(): LoginResponseModel {
    return LoginResponseModel(
        data = DataModel(
            id = this.data.id,
            type = this.data.type,
            attributes = AttributesModel(
                accessToken = this.data.attributes.accessToken,
                createdAt = this.data.attributes.createdAt,
                expiresIn = this.data.attributes.expiresIn,
                refreshToken = this.data.attributes.refreshToken,
                tokenType = this.data.attributes.tokenType
            )
        )
    )
}