package me.androidbox.data.local_clients

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import me.androidbox.data.models.SurveyListData

class RealmDbClient {

    var realm: Realm = Realm.open(
        configuration = RealmConfiguration.create(
            schema = setOf(
                SurveyListData::class
            )
        )
    )
}