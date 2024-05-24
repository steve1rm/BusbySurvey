package me.androidbox.data.di

import android.content.SharedPreferences
import io.ktor.client.HttpClient
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.local.imp.AuthorizationLocalDataSourceImp
import me.androidbox.data.repository.AuthorizationRepositoryImp
import me.androidbox.data.network_clients.HttpKtorClient
import me.androidbox.data.service.AuthorizationRemoteDataSource
import me.androidbox.data.service.imp.AuthorizationRemoteDataSourceImp
import me.androidbox.domain.authorization.AuthorizationRepository
import org.koin.dsl.module

val networkModule = module {

    single<HttpClient> { _ ->
       HttpKtorClient().build()
    }

    factory<AuthorizationRemoteDataSource> {
        AuthorizationRemoteDataSourceImp(get<HttpClient>())
    }

    factory<AuthorizationLocalDataSource> {
        AuthorizationLocalDataSourceImp(get<SharedPreferences>())
    }

    factory<AuthorizationRepository> {
        AuthorizationRepositoryImp(
            get<AuthorizationRemoteDataSource>(),
            get<AuthorizationLocalDataSource>())
    }
}