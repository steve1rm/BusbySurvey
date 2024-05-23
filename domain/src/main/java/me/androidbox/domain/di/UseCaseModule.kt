package me.androidbox.domain.di

import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.usecases.RegisterUseCase
import me.androidbox.domain.authorization.usecases.imp.RegisterUseCaseImp
import org.koin.dsl.module

val useCaseModule = module {

    factory<RegisterUseCase> {
        RegisterUseCaseImp(get<AuthorizationRepository>())
    }
}