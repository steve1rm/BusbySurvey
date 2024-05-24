package me.androidbox.domain.di

import me.androidbox.domain.repository.AuthorizationRepository
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import me.androidbox.domain.authorization.usecases.LoginUseCase
import me.androidbox.domain.authorization.usecases.RegisterUseCase
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.domain.authorization.usecases.imp.FetchTokenAuthorizationUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.LoginUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.RegisterUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.ResetPasswordUseCaseImp
import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.domain.survey.usecases.imp.FetchSurveyListUseCaseImp
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

    factory<FetchTokenAuthorizationUseCase> {
        FetchTokenAuthorizationUseCaseImp(get<AuthorizationRepository>())
    }

    factory<FetchSurveyListUseCase> {
        FetchSurveyListUseCaseImp(get<SurveyRepository>())
    }
}