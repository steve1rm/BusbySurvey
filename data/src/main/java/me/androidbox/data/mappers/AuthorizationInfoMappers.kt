package me.androidbox.data.mappers

import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.ResetPasswordDto
import me.androidbox.data.models.AuthorizationInfoSerializable
import me.androidbox.data.models.ErrorResponseDto
import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.authorization.models.AttributesModel
import me.androidbox.domain.authorization.models.DataModel
import me.androidbox.domain.authorization.models.ErrorModel
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.models.MetaModel
import me.androidbox.domain.authorization.models.ResetPasswordModel

fun AuthorizationInfo.toAuthorizationSerializable(): AuthorizationInfoSerializable {
    return AuthorizationInfoSerializable(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
    )
}

fun AuthorizationInfoSerializable.toAuthorizationInfo(): AuthorizationInfo {
    return AuthorizationInfo(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
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

fun ResetPasswordDto.toResetPasswordModel(): ResetPasswordModel {
    return ResetPasswordModel(
        meta = MetaModel(
            message = this.meta.message
        )
    )
}

fun ErrorResponseDto.toErrorResponseModel(): ErrorResponseModel {
    return ErrorResponseModel(
        errors = this.errors.map { errorDto ->
            ErrorModel(
                code = errorDto.code,
                detail = errorDto.detail
            )
        }
    )
}
