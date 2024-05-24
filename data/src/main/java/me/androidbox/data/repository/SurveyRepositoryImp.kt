package me.androidbox.data.repository

import me.androidbox.data.mappers.toSurveyListModel
import me.androidbox.data.service.SurveyRemoteDataSource
import me.androidbox.domain.repository.APIResponse
import me.androidbox.domain.repository.SurveyRepository
import me.androidbox.domain.survey.models.SurveyListModel

class SurveyRepositoryImp(
    private val surveyRemoteDataSource: SurveyRemoteDataSource,
) : SurveyRepository {

    override suspend fun fetchSurveyList(): APIResponse<SurveyListModel> {

       return when(val apiResponse = surveyRemoteDataSource.fetchSurveyList() ) {
           is APIResponse.OnSuccess -> {
               APIResponse.OnSuccess(data = apiResponse.data.toSurveyListModel())
           }
           is APIResponse.OnFailure -> {
               APIResponse.OnFailure(error = apiResponse.error)
           }

           else -> {
               throw IllegalStateException()
           }
       }
    }

    override suspend fun fetchSurveyDetails(): APIResponse<SurveyListModel> {
        surveyRemoteDataSource.fetchSurveyList()
        TODO()
    }
}