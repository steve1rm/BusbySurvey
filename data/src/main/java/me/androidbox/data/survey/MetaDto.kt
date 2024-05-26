package me.androidbox.data.survey


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaDto(
    val page: Int = 0,
    @SerialName("page_size")
    val pageSize: Int = 0,
    val pages: Int = 0,
    val records: Int = 0
)