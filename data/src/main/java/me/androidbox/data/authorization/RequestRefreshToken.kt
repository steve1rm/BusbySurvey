package me.androidbox.data.authorization


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestRefreshToken(
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String,
    @SerialName("grant_type")
    val grantType: String,
    @SerialName("refresh_token")
    val refreshToken: String
)