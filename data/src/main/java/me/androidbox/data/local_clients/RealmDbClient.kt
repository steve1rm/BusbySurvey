package me.androidbox.data.local_clients

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import me.androidbox.data.local.tables.SurveyListLocalTable

class RealmDbClient {

    var realm: Realm = Realm.open(
        configuration = RealmConfiguration.create(
            schema = setOf(
                SurveyListLocalTable::class
            )
        )
    )
}