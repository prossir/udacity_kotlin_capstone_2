package com.udacity.asteroidradar.domain.entity

import org.threeten.bp.OffsetDateTime


data class PictureOfTheDay(
    val copyright: String?,
    val date: String,
    val explanation: String,
    val mediaType: String,
    val title: String,
    val url: String,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime?
)