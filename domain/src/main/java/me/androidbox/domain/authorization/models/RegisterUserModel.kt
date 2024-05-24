package me.androidbox.domain.authorization.models

data class RegisterUserModel(
    val email: String,
    val name: String,
    val password: String,
    val passwordConfirmation: String
)