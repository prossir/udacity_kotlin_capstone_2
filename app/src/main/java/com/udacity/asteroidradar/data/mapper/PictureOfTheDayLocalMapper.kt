package com.udacity.asteroidradar.data.mapper

import com.udacity.asteroidradar.domain.entity.PictureOfTheDay
import com.udacity.asteroidradar.utils.db.models.PictureOfTheDayEntity
import com.udacity.asteroidradar.utils.mapper.SingleMapper
import org.threeten.bp.OffsetDateTime


class PictureOfTheDayLocalMapper: SingleMapper<PictureOfTheDayEntity, PictureOfTheDay>() {

    override fun map(value: PictureOfTheDayEntity) = PictureOfTheDay(
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