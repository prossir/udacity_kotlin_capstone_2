package com.udacity.asteroidradar.domain.use_case.picture_of_the_day

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.domain.entity.PictureOfTheDay
import com.udacity.asteroidradar.domain.repository.PictureOfTheDayRepository


class GetPictureOfTheDayUseCase(private val pictureOfTheDayRepository: PictureOfTheDayRepository) {

    operator fun invoke() : LiveData<PictureOfTheDay?> {
        return pictureOfTheDayRepository.getPictureOfTheDay()
    }

}