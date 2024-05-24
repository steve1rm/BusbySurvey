package me.androidbox.presentation.di

import me.androidbox.domain.authorization.usecases.LoginUseCase
import me.androidbox.presentation.authentication.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(get<LoginUseCase>())
    }
}