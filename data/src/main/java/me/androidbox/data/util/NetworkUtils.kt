package me.androidbox.data.util

import me.androidbox.domain.repository.APIResponse
import timber.log.Timber
import java.util.concurrent.CancellationException

inline fun <T> safeApiRequest(block: () -> T): APIResponse<T> {
    return try {
        val data = block()
        Timber.d("Response %s", data.toString())
        APIResponse.OnSuccess(data = data)
    } catch (exception: Exception) {
        if (exception is CancellationException) {
            Timber.e(exception)
            throw exception
        }
        else {
            Timber.e(exception)
            APIResponse.OnFailure(exception)
        }
    }
}
