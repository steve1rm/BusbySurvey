package me.androidbox.domain.survey.models


data class AttributesModel(
    val activeAt: String,
    val coverImageUrl: String,
    val createdAt: String,
    val description: String,
    val inactiveAt: Any?,
    val isActive: Boolean,
    val surveyType: String,
    val thankEmailAboveThreshold: String,
    val thankEmailBelowThreshold: String,
    val title: String
)