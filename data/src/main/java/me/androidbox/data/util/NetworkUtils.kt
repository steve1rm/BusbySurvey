package me.androidbox.data.util

import io.ktor.client.plugins.ResponseException
import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import timber.log.Timber
import java.util.concurrent.CancellationException

inline fun <D, E: DataError.Network> safeApiRequest(block: () -> D): CheckResult<D, E, Nothing> {
    return try {
        val data = block()
        Timber.d("Response %s", data.toString())
        CheckResult.Success(data = data)
    } catch (exception: ResponseException) {

        val code = exception.response.status.value
        Timber.e("Response Code %d", code)
        val result = when(code) {
            408 -> DataError.Network.REQUEST_TIMEOUT
            else -> {
                DataError.Network.UNKNOWN
            }
        }

        CheckResult.Failure(exceptionError = result)
    }
    catch (exception: Exception) {
        if (exception is CancellationException) {
            Timber.e(exception)
            throw exception
        }
        CheckResult.Failure(DataError.Network.UNKNOWN)
    }
}
