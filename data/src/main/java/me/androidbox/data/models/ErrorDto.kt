package me.androidbox.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    val code: String,
    val detail: String
)