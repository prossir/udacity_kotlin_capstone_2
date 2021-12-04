package com.udacity.asteroidradar.data.datasource.remote.dto.asteroid

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AsteroidResponse(
    @Json(name = FIELD_ID)
    val id: String,
    @Json(name = FIELD_NEO_REFERENCE_ID)
    val neoReferenceId: String,
    @Json(name = FIELD_NAME)
    val name: String,
    @Json(name = FIELD_NASA_JPL_URL)
    val nasaJplUrl: String,
    @Json(name = FIELD_ABSOLUTE_MAGNITUDE)
    val absoluteMagnitude: Double,
    @Json(name = FIELD_ESTIMATED_DIAMETER)
    val estimatedDiameter: EstimatedDiameterResponse,
    @Json(name = FIELD_IS_POTENTIALLY_HAZARDOUS)
    val isPotentiallyHazardous: Boolean,
    @Json(name = FIELD_CLOSE_APPROACH_DATA)
    val closeApproach: List<CloseApproachResponse>
) {

    companion object {
        private const val FIELD_ID = "id"
        private const val FIELD_NEO_REFERENCE_ID = "neo_reference_id"
        private const val FIELD_NAME = "name"
        private const val FIELD_NASA_JPL_URL = "nasa_jpl_url"
        private const val FIELD_ABSOLUTE_MAGNITUDE = "absolute_magnitude_h"
        private const val FIELD_ESTIMATED_DIAMETER = "estimated_diameter"
        private const val FIELD_IS_POTENTIALLY_HAZARDOUS = "is_potentially_hazardous_asteroid"
        private const val FIELD_CLOSE_APPROACH_DATA = "close_approach_data"

        const val UNKNOWN_CLOSE_APPROACH_VALUE = "Unknown"
    }

}