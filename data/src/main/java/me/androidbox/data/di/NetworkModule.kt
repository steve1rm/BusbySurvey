package me.androidbox.data.di

import io.ktor.client.HttpClient
import me.androidbox.data.authorization.AuthorizationRepositoryImp
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

    factory<AuthorizationRepository> {
        AuthorizationRepositoryImp(get<AuthorizationRemoteDataSource>())
    }
}