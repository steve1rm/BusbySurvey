package me.androidbox.busbynimblesurvey.di

import me.androidbox.busbynimblesurvey.MainViewModel
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import me.androidbox.domain.survey.usecases.FetchLocalSurveyListUseCase
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.domain.survey.usecases.WriteLocalSurveyListUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            get<FetchTokenAuthorizationUseCase>(),
            get<FetchSurveyListUseCase>(),
            get<FetchLocalSurveyListUseCase>(),
            get<WriteLocalSurveyListUseCase>())
    }
}