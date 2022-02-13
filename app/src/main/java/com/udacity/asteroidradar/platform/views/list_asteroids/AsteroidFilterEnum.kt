package com.udacity.asteroidradar.platform.views.list_asteroids


enum class AsteroidFilterEnum(val type: Int?) {
    NO_FILTER(null),
    DAILY(1),
    WEEKLY(7)
}