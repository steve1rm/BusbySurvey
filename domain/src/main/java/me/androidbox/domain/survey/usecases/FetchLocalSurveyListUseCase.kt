package me.androidbox.domain.survey.usecases

import kotlinx.coroutines.flow.Flow
import me.androidbox.domain.local.SurveyListLocalModel

interface FetchLocalSurveyListUseCase {
    suspend fun execute(): Flow<List<SurveyListLocalModel>>
}