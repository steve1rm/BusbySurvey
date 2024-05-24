package me.androidbox.domain.authorization.models

data class AttributesModel(
    val accessToken: String,
    val createdAt: Int,
    val expiresIn: Int,
    val refreshToken: String,
    val tokenType: String
)