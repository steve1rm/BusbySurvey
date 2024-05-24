package me.androidbox.data.authorization

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val email: String,
    val name: String,
    val password: String,
    @SerialName("password_confirmation")
    val passwordConfirmation: String,
)
