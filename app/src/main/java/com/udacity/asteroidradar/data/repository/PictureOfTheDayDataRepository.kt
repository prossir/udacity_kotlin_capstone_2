package com.udacity.asteroidradar.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.data.datasource.local.PictureOfTheDayLocalDataSource
import com.udacity.asteroidradar.data.datasource.remote.PictureOfTheDayRemoteDataSource
import com.udacity.asteroidradar.data.mapper.PictureOfTheDayLocalMapper
import com.udacity.asteroidradar.data.mapper.PictureOfTheDayRemoteMapper
import com.udacity.asteroidradar.domain.entity.PictureOfTheDay
import com.udacity.asteroidradar.domain.repository.PictureOfTheDayRepository
import com.udacity.asteroidradar.utils.extensions.yesterdayDate


class PictureOfTheDayDataRepository(
    private val pictureOfTheDayRemoteDataSource: PictureOfTheDayRemoteDataSource,
    private val pictureOfTheDayLocalDataSource: PictureOfTheDayLocalDataSource,
    private val pictureOfTheDayRemoteMapper: PictureOfTheDayRemoteMapper,
    private val pictureOfTheDayLocalMapper: PictureOfTheDayLocalMapper
) : PictureOfTheDayRepository {

    override suspend fun savePictureOfTheDay() : Boolean {
        pictureOfTheDayRemoteMapper.map(pictureOfTheDayRemoteDataSource.getPictureOfTheDay()).apply {
            return pictureOfTheDayLocalDataSource.insert(this) == 1
        }
    }

    override fun getPictureOfTheDay(): LiveData<PictureOfTheDay?> {
        return Transformations.map(pictureOfTheDayLocalDataSource.retrieveLiveCurrent()) { pictureOfTheDay ->
            pictureOfTheDay?.let { pictureOfTheDayLocalMapper.map(it) }
        }
    }

    override suspend fun cleanStalePicturesOfTheDay(): Boolean {
        pictureOfTheDayLocalDataSource.retrieveFromBefore(yesterdayDate).apply {
            return pictureOfTheDayLocalDataSource.delete(this) == this.count()
        }
    }

}