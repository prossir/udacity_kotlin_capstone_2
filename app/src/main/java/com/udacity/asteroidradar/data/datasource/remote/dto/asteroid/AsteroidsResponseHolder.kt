package com.udacity.asteroidradar.data.datasource.remote.dto.asteroid

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AsteroidsResponseHolder(
    @Json(name = FIELD_ELEMENT_COUNT)
    val elementCount: Int,
    @Json(name = FIELD_DATA)
    val data: Map<String, List<AsteroidResponse>>
)  {

    companion object {
        private const val FIELD_ELEMENT_COUNT = "element_count"
        private const val FIELD_DATA = "near_earth_objects"
    }

}