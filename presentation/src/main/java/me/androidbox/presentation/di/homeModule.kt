package me.androidbox.presentation.di

import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase
import me.androidbox.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel {
        HomeViewModel(
            get<FetchSurveyListUseCase>()
        )
    }
}