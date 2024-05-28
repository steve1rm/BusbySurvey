package me.androidbox.busbynimblesurvey.di

import me.androidbox.busbynimblesurvey.MainViewModel
import me.androidbox.domain.authorization.usecases.FetchTokenAuthorizationUseCase
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.presentation.NotificationHandler
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
    factory<NotificationHandler> {
        NotificationHandler(androidContext())
    }

    viewModel<MainViewModel> {
        MainViewModel(
            get<FetchTokenAuthorizationUseCase>(),
            get<FetchSurveyListUseCase>())
    }
}