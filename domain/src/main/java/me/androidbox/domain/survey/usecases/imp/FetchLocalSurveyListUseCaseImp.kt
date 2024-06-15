package me.androidbox.domain.survey.usecases.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.local.SurveyListLocalModel
import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.usecases.FetchLocalSurveyListUseCase

class FetchLocalSurveyListUseCaseImp(
    private val surveyRepository: SurveyRepository
) : FetchLocalSurveyListUseCase {
    override suspend fun execute(): Flow<List<SurveyListLocalModel>> {
        return surveyRepository.readLocalSurveyList()
    }
}