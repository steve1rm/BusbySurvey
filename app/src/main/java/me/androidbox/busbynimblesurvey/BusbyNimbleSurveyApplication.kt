package me.androidbox.busbynimblesurvey

import android.app.Application
import me.androidbox.data.di.localCacheModule
import me.androidbox.data.di.networkModule
import me.androidbox.domain.di.useCaseModule
import me.androidbox.presentation.di.authenticationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class BusbyNimbleSurveyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@BusbyNimbleSurveyApplication)
            modules(
                networkModule,
                localCacheModule,
                useCaseModule,
                authenticationModule
            )
        }
    }
}