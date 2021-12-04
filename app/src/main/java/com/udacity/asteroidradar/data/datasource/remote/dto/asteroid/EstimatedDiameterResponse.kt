package com.udacity.asteroidradar.data.datasource.remote.dto.asteroid

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EstimatedDiameterResponse(
    @Json(name = FIELD_KILOMETERS)
    val kilometers: MeasureResponse,
    @Json(name = FIELD_METERS)
    val meters: MeasureResponse,
    @Json(name = FIELD_MILES)
    val miles: MeasureResponse,
    @Json(name = FIELD_FEET)
    val feet: MeasureResponse
) {

    companion object {
        private const val FIELD_KILOMETERS = "kilometers"
        private const val FIELD_METERS = "meters"
        private const val FIELD_MILES = "miles"
        private const val FIELD_FEET = "feet"
    }

}