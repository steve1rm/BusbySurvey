package me.androidbox.domain

typealias RootError = Error

sealed interface CheckResult<out D, out E, out T> {
    data class Success<D>(val data: D) : CheckResult<D, Nothing, Nothing>
    data class Failure<out E: RootError, out T>(val exceptionError: RootError = DataError.Network.UNKNOWN, val responseError: T? = null) : CheckResult<Nothing, E, T>
}