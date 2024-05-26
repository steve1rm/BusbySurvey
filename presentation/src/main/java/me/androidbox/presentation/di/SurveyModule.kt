package me.androidbox.presentation.di

import me.androidbox.presentation.survey.SurveyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val surveyModule = module {
    viewModel<SurveyViewModel> {
        SurveyViewModel()
    }
}
