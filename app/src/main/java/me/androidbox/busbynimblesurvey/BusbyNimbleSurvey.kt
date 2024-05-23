package me.androidbox.busbynimblesurvey

import android.app.Application
import me.androidbox.data.di.localCacheModule
import me.androidbox.data.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BusbyNimbleSurvey : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BusbyNimbleSurvey)
            modules(
                networkModule,
                localCacheModule
            )
        }
    }
}