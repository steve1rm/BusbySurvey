package me.androidbox.data.authorization

import kotlinx.serialization.Serializable

@Serializable
data class DataDto(
    val attributes: AttributesDto,
    val id: String,
    val type: String
)