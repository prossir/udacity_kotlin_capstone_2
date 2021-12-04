package com.udacity.asteroidradar.data.datasource.remote.dto.asteroid

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RelativeVelocityResponse(
    @Json(name = FIELD_KILOMETERS_PER_SECOND)
    val kilometersPerSecond: String,
    @Json(name = FIELD_KILOMETERS_PER_HOUR)
    val kilometersPerHour: String,
    @Json(name = FIELD_MILES_PER_HOUR)
    val milesPerHour: String,
) {

    companion object {
        private const val FIELD_KILOMETERS_PER_SECOND = "kilometers_per_second"
        private const val FIELD_KILOMETERS_PER_HOUR = "kilometers_per_hour"
        private const val FIELD_MILES_PER_HOUR = "miles_per_hour"
    }

}