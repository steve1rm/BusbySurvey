package me.androidbox.data.local

import kotlinx.coroutines.flow.Flow
import me.androidbox.data.models.SurveyListData

interface SurveyListLocalDataSource {
    suspend fun saveSurveyList(surveyListData: SurveyListData)
    fun retrieveSurveyList(): Flow<List<SurveyListData>>
}