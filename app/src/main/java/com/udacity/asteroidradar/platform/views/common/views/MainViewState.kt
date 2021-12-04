package com.udacity.asteroidradar.platform.views.common.views


sealed class MainViewState {
    object SuccessInGettingAsteroids: MainViewState()
    object Failure: MainViewState()
}