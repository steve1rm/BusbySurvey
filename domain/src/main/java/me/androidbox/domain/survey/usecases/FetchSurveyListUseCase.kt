package me.androidbox.domain.survey.usecases

import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.survey.models.SurveyListModel

fun interface FetchSurveyListUseCase {
    suspend fun execute(): APIResponse<SurveyListModel>
}