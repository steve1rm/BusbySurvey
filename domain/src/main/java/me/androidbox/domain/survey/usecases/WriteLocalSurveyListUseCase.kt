package me.androidbox.domain.survey.usecases

fun interface WriteLocalSurveyListUseCase {
    suspend fun execute(title: String, description: String, imageUrl: String)
}