package me.androidbox.data.network_clients

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpKtorClient {

    fun build(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                this.level = LogLevel.ALL
/*
                if (BuildConfig.DEBUG) {
                    this.level = LogLevel.BODY
                } else {
                    this.level = LogLevel.NONE
                }
*/
            }
        }
    }
}