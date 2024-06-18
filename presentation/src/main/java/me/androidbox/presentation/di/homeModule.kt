package me.androidbox.presentation.di

import me.androidbox.domain.authorization.usecases.LogoutUserUseCase
import me.androidbox.domain.authorization.usecases.SetTokenAuthorizationUseCase
import me.androidbox.domain.survey.usecases.DeleteLocalSurveyListUseCase
import me.androidbox.domain.survey.usecases.FetchLocalSurveyListUseCase
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.domain.survey.usecases.WriteLocalSurveyListUseCase
import me.androidbox.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel {
        HomeViewModel(
            get<FetchSurveyListUseCase>(),
            get<LogoutUserUseCase>(),
            get<SetTokenAuthorizationUseCase>(),
            get<FetchLocalSurveyListUseCase>(),
            get<WriteLocalSurveyListUseCase>(),
            get<DeleteLocalSurveyListUseCase>()
        )
    }
}