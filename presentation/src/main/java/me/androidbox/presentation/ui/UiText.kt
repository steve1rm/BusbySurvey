package me.androidbox.presentation.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {
    data class DynamicString(val value: String) : UiText
    class StringResource(@StringRes val resId: Int, val args: Array<Any> = arrayOf()) : UiText
    data object Empty : UiText

    /** Used from composables */
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> {
                this.value
            }
            is StringResource -> {
                stringResource(id = this.resId, this.args)
            }
            is Empty -> {
                ""
            }
        }
    }

    /** Used for non-composables */
    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> {
                this.value
            }
            is StringResource -> {
                context.getString(this.resId, this.args)
            }
            is Empty -> {
                ""
            }
        }
    }
}
