package me.androidbox.domain.survey.usecases.imp

import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.usecases.WriteLocalSurveyListUseCase

class WriteLocalSurveyListUseCaseImp(
    private val surveyRepository: SurveyRepository
) : WriteLocalSurveyListUseCase {
    override suspend fun execute(title: String, description: String, imageUrl: String) {
        surveyRepository.writeLocalSurveyList(title, description, imageUrl)
    }
}