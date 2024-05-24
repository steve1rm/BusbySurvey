package me.androidbox.presentation.di

import me.androidbox.domain.authorization.usecases.LoginUseCase
import me.androidbox.domain.authorization.usecases.ResetPasswordUseCase
import me.androidbox.presentation.authentication.login.LoginViewModel
import me.androidbox.presentation.authentication.reset.ResetPasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(get<LoginUseCase>())
    }

    viewModel<ResetPasswordViewModel> {
        ResetPasswordViewModel(get<ResetPasswordUseCase>())
    }
}