package me.androidbox.domain.authorization.models

data class LoginRequestModel(
    val clientId: String = "",
    val clientSecret: String = "",
    val email: String,
    val grantType: String = "",
    val password: String
)