package me.androidbox.data.authorization


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordDto(
    @SerialName("meta")
    val meta: MetaModelDto
)