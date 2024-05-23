package me.androidbox.data.util

import me.androidbox.domain.repository.APIResponse
import java.util.concurrent.CancellationException

inline fun <T> safeApiRequest(block: () -> T): APIResponse<T> {
    return try {
        APIResponse.OnSuccess(data = block())
    } catch (exception: Exception) {
        if (exception is CancellationException) {
            throw exception
        }
        else {
            APIResponse.OnFailure(exception)
        }
    }
}
