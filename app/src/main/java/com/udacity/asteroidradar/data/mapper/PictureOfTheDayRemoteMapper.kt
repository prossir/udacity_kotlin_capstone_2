package com.udacity.asteroidradar.data.mapper

import com.udacity.asteroidradar.data.datasource.remote.dto.pictureOfTheDay.PictureOfTheDayResponse
import com.udacity.asteroidradar.utils.db.models.PictureOfTheDayEntity
import com.udacity.asteroidradar.utils.mapper.SingleMapper
import org.threeten.bp.OffsetDateTime


class PictureOfTheDayRemoteMapper: SingleMapper<PictureOfTheDayResponse, PictureOfTheDayEntity>() {

    override fun map(value: PictureOfTheDayResponse) = PictureOfTheDayEntity(
        date = value.date,
        copyright = value.copyright,
        explanation = value.explanation,
        mediaType = value.mediaType,
        title = value.title,
        url = value.url,
        createdAt = OffsetDateTime.now(),
        updatedAt = OffsetDateTime.now()
    )

}