package me.androidbox.domain.survey.models

data class SurveyListModel(
    val data: List<DataModel> = emptyList(),
    val meta: MetaModel = MetaModel()
)