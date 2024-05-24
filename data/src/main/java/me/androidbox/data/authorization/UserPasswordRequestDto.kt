package me.androidbox.data.authorization


import kotlinx.serialization.Serializable

@Serializable
data class UserPasswordRequestDto(
    val email: String
)