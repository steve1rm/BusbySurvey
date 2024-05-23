package me.androidbox.data.network_clients

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.androidbox.data.BuildConfig
import timber.log.Timber

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

         /*   defaultRequest {
                contentType(ContentType.Application.Json)
            }
*/
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.d(message)
                    }
                }

                if (BuildConfig.DEBUG) {
                    this.level = LogLevel.ALL
                } else {
                    this.level = LogLevel.NONE
                }
            }
        }
    }
}