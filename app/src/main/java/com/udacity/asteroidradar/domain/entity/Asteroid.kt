package com.udacity.asteroidradar.domain.entity


data class Asteroid(
    val id: String,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedMaximumDiameter: Double,
    val estimatedMinimumDiameter: Double,
    val relativeVelocity: String,
    val distanceFromOrbitingBody: String,
    val orbitingBody: String,
    val isPotentiallyHazardous: Boolean,
    var appearanceDate: String,
)