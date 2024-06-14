package me.androidbox.data.local.imp

import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.data.local.SurveyListLocalDataSource
import me.androidbox.data.local_clients.RealmDbClient
import me.androidbox.data.models.SurveyListData

class SurveyListLocalDataSourceImp(
    private val realmDbClient: RealmDbClient
) : SurveyListLocalDataSource {
    override suspend fun saveSurveyList(surveyListData: SurveyListData) {
        realmDbClient.realm.write {
            this.copyToRealm(
                instance = surveyListData,
                updatePolicy = UpdatePolicy.ALL
            )
        }
    }

    override fun retrieveSurveyList(): Flow<List<SurveyListData>> {
        return realmDbClient.realm
            .query(SurveyListData::class)
            .asFlow()
            .map { results ->
                results.list
                    .toList()
            }
    }
}