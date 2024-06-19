package me.androidbox.data.di

import android.content.SharedPreferences
import io.ktor.client.HttpClient
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.local.SurveyLocalDataSource
import me.androidbox.data.local.imp.AuthorizationLocalDataSourceImp
import me.androidbox.data.repository.AuthorizationRepositoryImp
import me.androidbox.data.network_clients.HttpKtorClient
import me.androidbox.data.repository.SurveyRepositoryImp
import me.androidbox.data.remote.AuthorizationRemoteDataSource
import me.androidbox.data.remote.SurveyRemoteDataSource
import me.androidbox.data.remote.imp.AuthorizationRemoteDataSourceImp
import me.androidbox.data.remote.imp.SurveyRemoteDataSourceImp
import me.androidbox.domain.repository.AuthorizationRepository
import me.androidbox.domain.repository.SurveyRepository
import org.koin.dsl.module

val networkModule = module {

    single<HttpClient> { _ ->
       HttpKtorClient(get<AuthorizationLocalDataSource>()).build()
    }

    factory<AuthorizationRemoteDataSource> {
        AuthorizationRemoteDataSourceImp(
            get<HttpClient>(),
            get<AuthorizationLocalDataSource>())
    }

    factory<AuthorizationLocalDataSource> {
        AuthorizationLocalDataSourceImp(get<SharedPreferences>())
    }

    factory<AuthorizationRepository> {
        AuthorizationRepositoryImp(
            get<AuthorizationRemoteDataSource>(),
            get<AuthorizationLocalDataSource>())
    }

    factory<SurveyRemoteDataSource> {
        SurveyRemoteDataSourceImp(
            get<HttpClient>(),
            get<AuthorizationLocalDataSource>())
    }

    factory<SurveyRepository> {
        SurveyRepositoryImp(
            get<SurveyRemoteDataSource>(),
            get<SurveyLocalDataSource>())
    }
}