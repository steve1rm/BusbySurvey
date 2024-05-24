package me.androidbox.data.survey


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataDto(
    val attributes: AttributesDto,
    @SerialName("id")
    val id: String,
    val type: String
)