package com.udacity.asteroidradar.data.mapper

import com.udacity.asteroidradar.domain.entity.Asteroid
import com.udacity.asteroidradar.utils.db.models.AsteroidEntity
import com.udacity.asteroidradar.utils.mapper.SingleMapper


class AsteroidLocalMapper: SingleMapper<AsteroidEntity, Asteroid>() {

    override fun map(value: AsteroidEntity) = Asteroid(
        id = value.id,
        codename = value.codename,
        closeApproachDate = value.closeApproachDate,
        absoluteMagnitude = value.absoluteMagnitude,
        estimatedMaximumDiameter = value.estimatedMaximumDiameter,
        estimatedMinimumDiameter = value.estimatedMinimumDiameter,
        relativeVelocity = value.relativeVelocity,
        distanceFromOrbitingBody = value.distanceFromOrbitingBody,
        orbitingBody = value.orbitingBody,
        isPotentiallyHazardous = value.isPotentiallyHazardous,
        appearanceDate = value.appearanceDate
    )

}