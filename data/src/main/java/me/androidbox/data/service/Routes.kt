package me.androidbox.data.service

import me.androidbox.data.BuildConfig

object Routes {
    private const val BASE_URL = BuildConfig.BASE_BUSBY_NIMBLE_ENDPOINT
    const val LOGIN = "$BASE_URL/oauth/token"
    const val RESET_PASSWORD = "$BASE_URL/passwords"
    const val LOGOUT = "$BASE_URL/oauth/revoke"
    const val REGISTER = "$BASE_URL/registrations"
}