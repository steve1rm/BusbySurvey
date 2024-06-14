package me.androidbox.data.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import me.androidbox.data.local.SurveyListLocalDataSource
import me.androidbox.data.local.imp.SurveyListLocalDataSourceImp
import me.androidbox.data.local_clients.RealmDbClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localCacheModule = module {

    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "secret_shared_prefs",
            MasterKey(androidApplication())
        )
    }

    single<RealmDbClient> {
        RealmDbClient()
    }

    factory<SurveyListLocalDataSource> {
        SurveyListLocalDataSourceImp(get<RealmDbClient>())
    }
}