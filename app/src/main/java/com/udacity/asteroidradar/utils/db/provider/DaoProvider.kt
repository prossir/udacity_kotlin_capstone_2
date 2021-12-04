package com.udacity.asteroidradar.utils.db.provider

import com.udacity.asteroidradar.utils.db.dao.AsteroidDao
import com.udacity.asteroidradar.utils.db.dao.PictureOfTheDayDao


class DaoProvider(private val database: DatabaseProvider) {

    fun getAsteroidDao(): AsteroidDao = database.getInstance().asteroidDao()
    fun getPictureOfTheDayDao(): PictureOfTheDayDao = database.getInstance().pictureOfTheDayDao()

}