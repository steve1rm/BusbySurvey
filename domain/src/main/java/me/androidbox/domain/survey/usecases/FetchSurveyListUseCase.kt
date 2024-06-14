package me.androidbox.domain.survey.usecases

import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.survey.models.SurveyListModel

fun interface FetchSurveyListUseCase {
    suspend fun execute(): CheckResult<SurveyListModel, DataError.Network, ErrorResponseModel>
}