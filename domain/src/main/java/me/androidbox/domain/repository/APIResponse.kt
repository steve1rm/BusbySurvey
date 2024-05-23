package me.androidbox.domain.repository

interface APIResponse<out T> {
    data class OnSuccess<T>(val data: T) : APIResponse<T>
    data class OnFailure(val error: Exception) : APIResponse<Nothing>
}