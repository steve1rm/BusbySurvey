package me.androidbox.domain

sealed interface CheckResult<out D, out E, out T> {
    data class Success<D>(val data: D) : CheckResult<D, Nothing, Nothing>
    data class Failure<out E: Error, T>(val exceptionError: Error = DataError.Network.NOTHING, val responseError: T? = null) : CheckResult<Nothing, E, T>
}