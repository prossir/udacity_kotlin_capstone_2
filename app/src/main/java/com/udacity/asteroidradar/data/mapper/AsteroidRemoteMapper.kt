package com.udacity.asteroidradar.data.mapper

import com.udacity.asteroidradar.data.datasource.remote.dto.asteroid.AsteroidResponse
import com.udacity.asteroidradar.utils.db.models.AsteroidEntity
import com.udacity.asteroidradar.utils.extensions.EMPTY
import com.udacity.asteroidradar.utils.mapper.SingleMapper
import org.threeten.bp.OffsetDateTime


class AsteroidRemoteMapper: SingleMapper<AsteroidResponse, AsteroidEntity>() {

    override fun map(value: AsteroidResponse) = AsteroidEntity(
        id = value.id,
        codename = value.name,
        closeApproachDate = if(value.closeApproach.isNotEmpty()) value.closeApproach[0].closeApproachDateFull else AsteroidResponse.UNKNOWN_CLOSE_APPROACH_VALUE,
        absoluteMagnitude = value.absoluteMagnitude,
        estimatedMaximumDiameter = value.estimatedDiameter.kilometers.maximumDiameter,
        estimatedMinimumDiameter = value.estimatedDiameter.kilometers.minimumDiameter,
        relativeVelocity = if(value.closeApproach.isNotEmpty()) value.closeApproach[0].relativeVelocity.kilometersPerHour else AsteroidResponse.UNKNOWN_CLOSE_APPROACH_VALUE,
        distanceFromOrbitingBody = if(value.closeApproach.isNotEmpty()) value.closeApproach[0].missDistance.kilometers else AsteroidResponse.UNKNOWN_CLOSE_APPROACH_VALUE,
        orbitingBody = if(value.closeApproach.isNotEmpty()) value.closeApproach[0].orbitingBody else AsteroidResponse.UNKNOWN_CLOSE_APPROACH_VALUE,
        isPotentiallyHazardous = value.isPotentiallyHazardous,
        appearanceDate = EMPTY,
        createdAt = OffsetDateTime.now()
    )

}