package com.udacity.asteroidradar.data.datasource.remote.dto.asteroid

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MissDistanceResponse(
    @Json(name = FIELD_ASTRONOMICAL)
    val astronomical: String,
    @Json(name = FIELD_LUNAR)
    val lunar: String,
    @Json(name = FIELD_KILOMETERS)
    val kilometers: String,
    @Json(name = FIELD_MILES)
    val miles: String
) {

    companion object {
        private const val FIELD_ASTRONOMICAL = "astronomical"
        private const val FIELD_LUNAR = "lunar"
        private const val FIELD_KILOMETERS = "kilometers"
        private const val FIELD_MILES = "miles"
    }

}