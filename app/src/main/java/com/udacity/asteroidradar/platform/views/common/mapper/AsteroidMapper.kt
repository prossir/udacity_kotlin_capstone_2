package com.udacity.asteroidradar.platform.views.common.mapper

import com.udacity.asteroidradar.domain.entity.Asteroid
import com.udacity.asteroidradar.platform.views.common.model.AsteroidModel
import com.udacity.asteroidradar.utils.mapper.SingleMapper


class AsteroidMapper: SingleMapper<Asteroid, AsteroidModel>() {

    override fun map(value: Asteroid) = AsteroidModel(
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