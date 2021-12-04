package com.udacity.asteroidradar.data.datasource.remote.dto.asteroid

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CloseApproachResponse(
    @Json(name = FIELD_CLOSE_APPROACH_DATE)
    val closeApproachDate: String,
    @Json(name = FIELD_CLOSE_APPROACH_DATE_FULL)
    val closeApproachDateFull: String,
    @Json(name = FIELD_RELATIVE_VELOCITY)
    val relativeVelocity: RelativeVelocityResponse,
    @Json(name = FIELD_MISS_DISTANCE)
    val missDistance: MissDistanceResponse,
    @Json(name = FIELD_ORBITING_BODY)
    val orbitingBody: String
) {

    companion object {
        private const val FIELD_CLOSE_APPROACH_DATE = "close_approach_date"
        private const val FIELD_CLOSE_APPROACH_DATE_FULL = "close_approach_date_full"
        private const val FIELD_RELATIVE_VELOCITY = "relative_velocity"
        private const val FIELD_MISS_DISTANCE = "miss_distance"
        private const val FIELD_ORBITING_BODY = "orbiting_body"
    }

}