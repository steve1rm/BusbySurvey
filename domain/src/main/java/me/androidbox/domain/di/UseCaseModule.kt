package me.androidbox.domain.di

import me.androidbox.domain.repository.AuthorizationRepository
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import me.androidbox.domain.authorization.usecases.LoginUseCase
import me.androidbox.domain.authorization.usecases.LogoutUserUseCase
import me.androidbox.domain.authorization.usecases.RegisterUseCase
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.domain.authorization.usecases.SetTokenAuthorizationUseCase
import me.androidbox.domain.authorization.usecases.imp.FetchTokenAuthorizationUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.LoginUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.LogoutUserUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.RegisterUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.ResetPasswordUseCaseImp
import me.androidbox.domain.authorization.usecases.imp.SetTokenAuthorizationUseCaseImp
import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.usecases.FetchLocalSurveyListUseCase
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.domain.survey.usecases.WriteLocalSurveyListUseCase
import me.androidbox.domain.survey.usecases.imp.FetchLocalSurveyListUseCaseImp
import me.androidbox.domain.survey.usecases.imp.FetchSurveyListUseCaseImp
import me.androidbox.domain.survey.usecases.imp.WriteLocalSurveyListUseCaseImp
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

    factory<SetTokenAuthorizationUseCase> {
        SetTokenAuthorizationUseCaseImp(get<AuthorizationRepository>())
    }

    factory<FetchSurveyListUseCase> {
        FetchSurveyListUseCaseImp(get<SurveyRepository>())
    }

    factory<LogoutUserUseCase> {
        LogoutUserUseCaseImp(get<AuthorizationRepository>())
    }

    factory<FetchLocalSurveyListUseCase> {
        FetchLocalSurveyListUseCaseImp(get<SurveyRepository>())
    }

    factory<WriteLocalSurveyListUseCase> {
        WriteLocalSurveyListUseCaseImp(get<SurveyRepository>())
    }
}