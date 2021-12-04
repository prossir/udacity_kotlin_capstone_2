package com.udacity.asteroidradar.platform.views.common.model


data class PictureOfTheDayModel(
    val date: String,
    val copyright: String?,
    val explanation: String,
    val mediaType: String,
    val title: String,
    val url: String,
)