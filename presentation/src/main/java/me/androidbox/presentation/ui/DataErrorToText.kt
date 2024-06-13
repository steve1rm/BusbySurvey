package me.androidbox.presentation.ui

import android.content.Context
import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.presentation.R

fun DataError.toUiText(): UiText {
    return when(this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(
            resId = R.string.error_disk_full
        )
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            resId = R.string.error_request_timeout
        )
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
            resId = R.string.error_too_many_requests
        )
        DataError.Network.NO_INTERNET -> UiText.StringResource(
            resId = R.string.error_no_internet
        )
        DataError.Network.PAYLOAD_TOO_LARGE -> UiText.StringResource(
            resId = R.string.error_payload_too_large
        )
        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            resId = R.string.error_server_error
        )
        DataError.Network.SERIALIZATION -> UiText.StringResource(
            resId = R.string.error_serialization
        )
        else -> UiText.StringResource(
            resId = R.string.error_unknown
        )
    }
}

fun CheckResult.Failure<DataError, ErrorResponseModel>.asErrorText(): UiText {
    return if(this.responseError != null) {
        UiText.DynamicString(this.responseError?.errors?.first()?.detail ?: "")
    }
    else {
        (this.exceptionError as DataError).toUiText()
    }
}