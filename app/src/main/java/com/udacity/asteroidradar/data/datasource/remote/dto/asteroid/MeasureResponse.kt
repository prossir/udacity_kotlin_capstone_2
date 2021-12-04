package com.udacity.asteroidradar.data.datasource.remote.dto.asteroid

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class MeasureResponse(
    @Json(name = FIELD_MINIMUM_DIAMETER)
    val minimumDiameter: Double,
    @Json(name = FIELD_MAXIMUM_DIAMETER)
    val maximumDiameter: Double
) {

    companion object {
        private const val FIELD_MINIMUM_DIAMETER = "estimated_diameter_min"
        private const val FIELD_MAXIMUM_DIAMETER = "estimated_diameter_max"
    }

}