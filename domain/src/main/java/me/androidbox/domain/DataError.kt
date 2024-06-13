package me.androidbox.domain

sealed interface DataError : Error {

    enum class Network : DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        CONFLICT,
        UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL
    }
}