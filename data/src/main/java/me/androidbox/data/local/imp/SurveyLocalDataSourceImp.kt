package me.androidbox.data.local.imp

import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.data.local.SurveyLocalDataSource
import me.androidbox.data.local_clients.RealmDbClient
import me.androidbox.data.local.tables.SurveyListLocalTable
import me.androidbox.domain.local.SurveyListLocalModel

class SurveyLocalDataSourceImp(
    private val realmDbClient: RealmDbClient
) : SurveyLocalDataSource {
    override suspend fun saveSurveyList(title: String, description: String, imageUrl: String) {
        realmDbClient.realm.write {
            this.copyToRealm(
                instance = SurveyListLocalTable().apply {
                    this.title = title
                    this.description = description
                    this.imageUrl = imageUrl
                },
                updatePolicy = UpdatePolicy.ALL
            )
        }
    }

    override fun retrieveSurveyList(): Flow<List<SurveyListLocalTable>> {
        return realmDbClient.realm
            .query(SurveyListLocalTable::class)
            .asFlow()
            .map { results ->
                results.list
                    .toList()
            }
    }
}