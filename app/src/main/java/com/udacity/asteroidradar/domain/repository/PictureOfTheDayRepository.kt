package com.udacity.asteroidradar.domain.repository

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.domain.entity.PictureOfTheDay


interface PictureOfTheDayRepository {

    suspend fun savePictureOfTheDay() : Boolean
    fun getPictureOfTheDay(): LiveData<PictureOfTheDay?>
    suspend fun cleanStalePicturesOfTheDay(): Boolean

}