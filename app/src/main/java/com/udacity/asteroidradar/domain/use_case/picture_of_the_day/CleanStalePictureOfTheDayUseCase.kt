package com.udacity.asteroidradar.domain.use_case.picture_of_the_day

import com.udacity.asteroidradar.domain.repository.PictureOfTheDayRepository


class CleanStalePictureOfTheDayUseCase(private val pictureOfTheDayRepository: PictureOfTheDayRepository) {

    suspend operator fun invoke() : Boolean {
        return pictureOfTheDayRepository.cleanStalePicturesOfTheDay()
    }

}