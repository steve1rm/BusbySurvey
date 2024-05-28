package me.androidbox.data.mappers

import me.androidbox.data.survey.SurveyListDto
import me.androidbox.domain.survey.models.AttributesModel
import me.androidbox.domain.survey.models.DataModel
import me.androidbox.domain.survey.models.MetaModel
import me.androidbox.domain.survey.models.SurveyListModel

fun SurveyListDto.toSurveyListModel(): SurveyListModel {
    return SurveyListModel(
        meta = MetaModel(
            page = this.meta.page,
            pages = this.meta.pages,
            pageSize = this.meta.pageSize,
            records = this.meta.records
        ),
        data = this.data.map { dataDto ->
            DataModel(
                attributes = AttributesModel(
                    activeAt = dataDto.attributes.activeAt,
                    coverImageUrl = dataDto.attributes.coverImageUrl,
                    createdAt = dataDto.attributes.createdAt,
                    description = dataDto.attributes.description,
                    inactiveAt = dataDto.attributes.inactiveAt,
                    isActive = dataDto.attributes.isActive,
                    surveyType = dataDto.attributes.surveyType,
                    thankEmailAboveThreshold = dataDto.attributes.thankEmailAboveThreshold,
                    thankEmailBelowThreshold = dataDto.attributes.thankEmailBelowThreshold,
                    title = dataDto.attributes.title),
                id = dataDto.id,
                type = dataDto.type)
        }
    )
}