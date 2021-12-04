package com.udacity.asteroidradar.platform.views.common.mapper

import com.udacity.asteroidradar.domain.entity.PictureOfTheDay
import com.udacity.asteroidradar.platform.views.common.model.PictureOfTheDayModel
import com.udacity.asteroidradar.utils.mapper.SingleMapper


class PictureOfTheDayMapper: SingleMapper<PictureOfTheDay, PictureOfTheDayModel>() {

    override fun map(value: PictureOfTheDay) = PictureOfTheDayModel(
        date = value.date,
        copyright = value.copyright,
        explanation = value.explanation,
        mediaType = value.mediaType,
        title = value.title,
        url = value.url
    )

}