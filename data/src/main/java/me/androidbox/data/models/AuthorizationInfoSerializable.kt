package me.androidbox.data.models

import kotlinx.serialization.Serializable

@Serializable
class AuthorizationInfoSerializable(
    /** Short lived token */
    val accessToken: String,
    /** Long lived token to be used to request another accessToken */
    val refreshToken: String
)