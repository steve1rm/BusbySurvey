package me.androidbox.data.authorization

import kotlinx.serialization.Serializable

@Serializable
data class ClientCredentialsDto(
    val clientKey: String ,
    val clientSecret: String
)
