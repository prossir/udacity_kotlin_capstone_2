package com.udacity.asteroidradar.utils.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime


@Entity(
    tableName = AsteroidEntity.TABLE_NAME,
    indices = [
        Index(value = [AsteroidEntity.FIELD_ID])
    ]
)
data class AsteroidEntity(
    @ColumnInfo(name = FIELD_ID)
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = FIELD_CODENAME)
    val codename: String,
    @ColumnInfo(name = FIELD_CLOSE_APPROACH_DATE)
    val closeApproachDate: String,
    @ColumnInfo(name = FIELD_ABSOLUTE_MAGNITUDE)
    val absoluteMagnitude: Double,
    @ColumnInfo(name = FIELD_ESTIMATED_MAX_DIAMETER)
    val estimatedMaximumDiameter: Double,
    @ColumnInfo(name = FIELD_ESTIMATED_MIN_DIAMETER)
    val estimatedMinimumDiameter: Double,
    @ColumnInfo(name = FIELD_RELATIVE_VELOCITY)
    val relativeVelocity: String,
    @ColumnInfo(name = FIELD_DISTANCE_FROM_ORBITING_BODY)
    val distanceFromOrbitingBody: String,
    @ColumnInfo(name = FIELD_ORBITING_BODY)
    val orbitingBody: String,
    @ColumnInfo(name = FIELD_IS_POTENTIALLY_HAZARDOUS)
    val isPotentiallyHazardous: Boolean,
    @ColumnInfo(name = FIELD_APPEARANCE_DATE)
    var appearanceDate: String,
    @ColumnInfo(name = FIELD_CREATED_AT)
    var createdAt: OffsetDateTime?,
    @ColumnInfo(name = FIELD_UPDATED_AT)
    var updatedAt: OffsetDateTime? = createdAt
) {

    companion object {
        internal const val TABLE_NAME = "asteroid"
        internal const val FIELD_ID = "id"
        private const val FIELD_CODENAME = "codename"
        private const val FIELD_CLOSE_APPROACH_DATE = "close_approach_date"
        private const val FIELD_ABSOLUTE_MAGNITUDE = "absolute_magnitude"
        private const val FIELD_ESTIMATED_MAX_DIAMETER = "estimated_max_diameter"
        private const val FIELD_ESTIMATED_MIN_DIAMETER = "estimated_min_diameter"
        private const val FIELD_RELATIVE_VELOCITY = "relative_velocity"
        private const val FIELD_DISTANCE_FROM_ORBITING_BODY = "distance_from_orbiting_body"
        private const val FIELD_ORBITING_BODY = "orbiting_body"
        private const val FIELD_IS_POTENTIALLY_HAZARDOUS = "is_potentially_hazardous"
        private const val FIELD_APPEARANCE_DATE = "appearance_date"
        private const val FIELD_CREATED_AT = "created_at"
        private const val FIELD_UPDATED_AT = "updated_at"
    }

}