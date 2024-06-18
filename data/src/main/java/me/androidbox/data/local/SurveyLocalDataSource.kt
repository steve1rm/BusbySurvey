package me.androidbox.data.local

import kotlinx.coroutines.flow.Flow
import me.androidbox.data.local.tables.SurveyListLocalTable

interface SurveyLocalDataSource {
    suspend fun saveSurveyList(title: String, description: String, imageUrl: String)
    fun retrieveSurveyList(): Flow<List<SurveyListLocalTable>>
    suspend fun deleteSurveyList()
}