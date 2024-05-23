package me.androidbox.domain.repository

interface APIResponse<T> {
    data class OnSuccess<T>(val data: T) : APIResponse<T>
    data class OnFailure(val error: Exception) : APIResponse<Nothing>
}