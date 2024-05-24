package me.androidbox.data.survey


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaDto(
    val page: Int,
    @SerialName("page_size")
    val pageSize: Int,
    val pages: Int,
    val records: Int
)