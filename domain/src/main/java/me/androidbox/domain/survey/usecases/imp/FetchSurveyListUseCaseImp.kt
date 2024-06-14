package me.androidbox.domain.survey.usecases.imp

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.models.SurveyListModel
import me.androidbox.domain.survey.usecases.FetchSurveyListUseCase

class FetchSurveyListUseCaseImp(
    private val surveyRepository: SurveyRepository
) : FetchSurveyListUseCase {
    override suspend fun execute(): CheckResult<SurveyListModel, DataError.Network, ErrorResponseModel> {
        return surveyRepository.fetchSurveyList()
    }
}