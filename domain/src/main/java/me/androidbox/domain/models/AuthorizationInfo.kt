package me.androidbox.domain.models

data class AuthorizationInfo(
    /** Short lived token */
    val accessToken: String,
    /** Long lived token to be used to request another accessToken */
    val refreshToken: String,
    val userId: String
)
