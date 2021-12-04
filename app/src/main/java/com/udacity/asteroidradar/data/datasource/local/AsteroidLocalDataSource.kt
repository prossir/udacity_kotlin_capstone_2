package com.udacity.asteroidradar.data.datasource.local

import com.udacity.asteroidradar.utils.db.models.AsteroidEntity
import com.udacity.asteroidradar.utils.db.provider.DaoProvider


class AsteroidLocalDataSource(
    daoProvider: DaoProvider
) {

    private val asteroidDao = daoProvider.getAsteroidDao()

    fun retrieveFromBefore(date: String): List<AsteroidEntity> {
        return asteroidDao.findFromBefore(date)
    }

    fun retrieveAll() =
        asteroidDao.retrieveAll()

    fun insertAll(asteroids: List<AsteroidEntity>) : Int {
        return asteroidDao.insertAll(asteroids).count()
    }

    fun delete(oldAsteroidsToDelete: List<AsteroidEntity>) : Int {
        return asteroidDao.delete(oldAsteroidsToDelete)
    }

}