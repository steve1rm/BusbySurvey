package me.androidbox.domain.di

import me.androidbox.domain.authorization.AuthorizationRepository
import me.androidbox.domain.authorization.usecases.LoginUseCase
import me.androidbox.domain.authorization.usecases.RegisterUseCase
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.domain.authorization.usecases.imp.LoginUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.RegisterUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.ResetPasswordUseCaseImp
import org.koin.dsl.module

val useCaseModule = module {

    factory<RegisterUseCase> {
        RegisterUseCaseImp(get<AuthorizationRepository>())
    }

    factory<LoginUseCase> {
        LoginUseCaseImp(get<AuthorizationRepository>())
    }

    factory<ResetPasswordUseCase> {
        ResetPasswordUseCaseImp(get<AuthorizationRepository>())
    }
}