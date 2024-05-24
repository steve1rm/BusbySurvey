package me.androidbox.busbynimblesurvey

import me.androidbox.presentation.NotificationHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory<NotificationHandler> {
        NotificationHandler(androidContext())
    }
}