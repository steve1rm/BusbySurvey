package me.androidbox.data.authorization

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val data: DataDto
)