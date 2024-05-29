package me.androidbox.data.local.imp

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.mappers.toAuthorizationInfo
import me.androidbox.data.mappers.toAuthorizationSerializable
import me.androidbox.data.models.AuthorizationInfoSerializable
import me.androidbox.domain.authorization.AuthorizationInfo

class AuthorizationLocalDataSourceImp(
    private val sharedPreferences: SharedPreferences
) : AuthorizationLocalDataSource {

    companion object {
        private const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }

    override suspend fun get(): AuthorizationInfo? {
        return withContext(Dispatchers.IO) {
            val json = sharedPreferences.getString(KEY_AUTH_INFO, null)

            json?.let {
                Json.decodeFromString<AuthorizationInfoSerializable>(json).toAuthorizationInfo()
            }
        }
    }

    override suspend fun set(authorizationInfo: AuthorizationInfo?) {
        if (authorizationInfo == null) {
            withContext(Dispatchers.IO) {
                sharedPreferences
                    .edit {
                        this.remove(KEY_AUTH_INFO)
                        this.apply()
                    }
            }
        }
        else {
            val json = Json.encodeToString(authorizationInfo.toAuthorizationSerializable())

            sharedPreferences
                .edit {
                    this.putString(KEY_AUTH_INFO, json)
                    this.apply()
                }
        }
    }
}

