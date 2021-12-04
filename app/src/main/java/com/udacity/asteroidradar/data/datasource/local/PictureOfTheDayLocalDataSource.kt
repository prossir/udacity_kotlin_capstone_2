package com.udacity.asteroidradar.data.datasource.local

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.utils.db.models.PictureOfTheDayEntity
import com.udacity.asteroidradar.utils.db.provider.DaoProvider
import com.udacity.asteroidradar.utils.extensions.formattedToday


class PictureOfTheDayLocalDataSource(
    daoProvider: DaoProvider
) {

    private val pictureOfTheDayDao = daoProvider.getPictureOfTheDayDao()

    fun retrieveLiveCurrent(): LiveData<PictureOfTheDayEntity?> {
        return pictureOfTheDayDao.findLiveByDate(formattedToday)
    }

    fun retrieveFromBefore(date: String): List<PictureOfTheDayEntity> {
        return pictureOfTheDayDao.findFromBefore(date)
    }

    suspend fun insert(pictureOfTheDay: PictureOfTheDayEntity) : Int {
        val result = pictureOfTheDayDao.insert(pictureOfTheDay)
        return if(result != 0L) 1 else 0
    }

    fun delete(oldPicturesOfTheDay: List<PictureOfTheDayEntity>): Int {
        return pictureOfTheDayDao.delete(oldPicturesOfTheDay)
    }

}