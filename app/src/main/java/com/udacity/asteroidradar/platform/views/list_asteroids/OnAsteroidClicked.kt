package com.udacity.asteroidradar.platform.views.list_asteroids

import com.udacity.asteroidradar.platform.views.common.model.AsteroidModel

interface OnAsteroidClicked {
    fun onAsteroidClickedListener(asteroid: AsteroidModel)
}