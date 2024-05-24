package me.androidbox.data.survey


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttributesDto(
    @SerialName("active_at")
    val activeAt: String,
    @SerialName("cover_image_url")
    val coverImageUrl: String,
    @SerialName("created_at")
    val createdAt: String,
    val description: String,
    @SerialName("inactive_at")
    @Contextual
    val inactiveAt: Any?,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("survey_type")
    val surveyType: String,
    @SerialName("thank_email_above_threshold")
    val thankEmailAboveThreshold: String = "",
    @SerialName("thank_email_below_threshold")
    val thankEmailBelowThreshold: String = "",
    val title: String
)