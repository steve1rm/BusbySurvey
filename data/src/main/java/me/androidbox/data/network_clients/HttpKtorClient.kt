package me.androidbox.data.network_clients

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.androidbox.data.BuildConfig
import me.androidbox.data.authorization.LoginResponseDto
import me.androidbox.data.authorization.RequestRefreshToken
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.domain.authorization.AuthorizationInfo
import timber.log.Timber

class HttpKtorClient(
    private val authorizationLocalDataSource: AuthorizationLocalDataSource
) {

    fun build(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    }
                )
            }

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

            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }

            install(Auth) {
                this.bearer {
                    this.loadTokens {
                        authorizationLocalDataSource.get()?.let { authorization ->
                            BearerTokens(
                                accessToken = authorization.accessToken,
                                refreshToken = authorization.refreshToken
                            )
                        }
                    }

                    this.refreshTokens {
                        val authorizationInfo = authorizationLocalDataSource.get()

                        authorizationInfo?.let {
                            val requestedBearerTokens = client.post("https://survey-api.nimblehq.co/api/v1/oauth/token") {
                                    setBody(
                                        RequestRefreshToken(
                                            grantType = "refresh_token",
                                            refreshToken = it.refreshToken,
                                            clientId = BuildConfig.CLIENT_KEY,
                                            clientSecret = BuildConfig.CLIENT_SECRET
                                        )
                                    )
                                }
                                .body<LoginResponseDto?>()

                            /** Save updated token to the cache */
                            if(requestedBearerTokens != null) {
                                val authorizationInfo = AuthorizationInfo(
                                    accessToken = requestedBearerTokens.data.attributes.accessToken,
                                    refreshToken = requestedBearerTokens.data.attributes.refreshToken
                                )

                                authorizationLocalDataSource.set(authorizationInfo)

                                /** Updated tokens */
                                BearerTokens(
                                    accessToken = requestedBearerTokens.data.attributes.accessToken,
                                    refreshToken = requestedBearerTokens.data.attributes.refreshToken
                                )
                            }
                            else {
                                /** Just return empty as request failed to get tokens */
                                BearerTokens(
                                    accessToken = "",
                                    refreshToken = ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}