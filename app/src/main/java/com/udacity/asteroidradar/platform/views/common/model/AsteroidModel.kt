package com.udacity.asteroidradar.platform.views.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AsteroidModel(
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
    var appearanceDate: String
) : Parcelable {

    val estimatedDiameter : Double
        get() = (estimatedMinimumDiameter + estimatedMaximumDiameter) / 2

}