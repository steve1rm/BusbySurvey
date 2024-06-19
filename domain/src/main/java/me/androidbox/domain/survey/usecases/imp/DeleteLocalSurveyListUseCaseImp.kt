package me.androidbox.domain.survey.usecases.imp

import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.usecases.DeleteLocalSurveyListUseCase

class DeleteLocalSurveyListUseCaseImp(
    private val surveyRepository: SurveyRepository
) : DeleteLocalSurveyListUseCase {

    override suspend fun execute() {
        surveyRepository.deleteSurveyList()
    }
}