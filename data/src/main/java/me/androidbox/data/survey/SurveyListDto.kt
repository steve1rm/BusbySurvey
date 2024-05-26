package me.androidbox.data.survey


import kotlinx.serialization.Serializable

@Serializable
data class SurveyListDto(
    val data: List<DataDto> = emptyList(),
    val meta: MetaDto = MetaDto()
)