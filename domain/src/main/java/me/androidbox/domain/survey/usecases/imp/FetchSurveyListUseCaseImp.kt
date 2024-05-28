package me.androidbox.domain.survey.usecases.imp

import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.models.SurveyListModel
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase

class FetchSurveyListUseCaseImp(
    private val surveyRepository: SurveyRepository
) : FetchSurveyListUseCase {
    override suspend fun execute(): APIResponse<SurveyListModel> {
        return surveyRepository.fetchSurveyList()
    }
}