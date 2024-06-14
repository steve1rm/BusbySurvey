package me.androidbox.data.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class SurveyList {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var surveyListData: RealmList<SurveyListData> = realmListOf()
}