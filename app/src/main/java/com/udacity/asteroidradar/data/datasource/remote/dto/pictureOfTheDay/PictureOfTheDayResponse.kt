package com.udacity.asteroidradar.data.datasource.remote.dto.pictureOfTheDay

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PictureOfTheDayResponse(
    @Json(name = FIELD_COPYRIGHT)
    val copyright: String?,
    @Json(name = FIELD_DATE)
    val date: String,
    @Json(name = FIELD_EXPLANATION)
    val explanation: String,
    @Json(name = FIELD_MEDIA_TYPE)
    val mediaType: String,
    @Json(name = FIELD_TITLE)
    val title: String,
    @Json(name = FIELD_URL)
    val url: String,
) {

    companion object {
        private const val FIELD_COPYRIGHT = "copyright"
        private const val FIELD_DATE = "date"
        private const val FIELD_EXPLANATION = "explanation"
        private const val FIELD_MEDIA_TYPE = "media_type"
        private const val FIELD_TITLE = "title"
        private const val FIELD_URL = "url"
    }

}