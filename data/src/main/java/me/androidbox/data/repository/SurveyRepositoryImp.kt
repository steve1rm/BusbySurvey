package me.androidbox.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.data.local.SurveyLocalDataSource
import me.androidbox.data.mappers.toErrorResponseModel
import me.androidbox.data.mappers.toSurveyListLocalModel
import me.androidbox.data.mappers.toSurveyListModel
import me.androidbox.data.remote.SurveyRemoteDataSource
import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.local.SurveyListLocalModel
import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.models.SurveyListModel

class SurveyRepositoryImp(
    private val surveyRemoteDataSource: SurveyRemoteDataSource,
    private val surveyLocalDataSource: SurveyLocalDataSource
) : SurveyRepository {

    override suspend fun fetchSurveyList(): CheckResult<SurveyListModel, DataError.Network, ErrorResponseModel> {

       return when(val apiResponse = surveyRemoteDataSource.fetchSurveyList() ) {
           is CheckResult.Success -> {
               CheckResult.Success(data = apiResponse.data.toSurveyListModel())
           }
           is CheckResult.Failure -> {
               CheckResult.Failure(
                   exceptionError = apiResponse.exceptionError,
                   responseError = apiResponse.responseError!!.toErrorResponseModel()
               )
           }
       }
    }

    override suspend fun fetchSurveyDetails(): APIResponse<SurveyListModel> {
        TODO("Survey Details not supported")
    }

    override suspend fun writeLocalSurveyList(title: String, description: String, imageUrl: String) {
        surveyLocalDataSource.saveSurveyList(title, description, imageUrl)
    }

    override fun readLocalSurveyList(): Flow<List<SurveyListLocalModel>> {
        val result = surveyLocalDataSource.retrieveSurveyList()
            .map { listOfSurvey ->
                listOfSurvey.map {
                    it.toSurveyListLocalModel()
                }
            }

        return result
    }
}