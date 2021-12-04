package com.udacity.asteroidradar.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.data.datasource.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.data.datasource.remote.AsteroidRemoteDataSource
import com.udacity.asteroidradar.data.mapper.AsteroidLocalMapper
import com.udacity.asteroidradar.data.mapper.AsteroidRemoteMapper
import com.udacity.asteroidradar.domain.entity.Asteroid
import com.udacity.asteroidradar.domain.repository.AsteroidRepository
import com.udacity.asteroidradar.utils.db.models.AsteroidEntity
import com.udacity.asteroidradar.utils.extensions.formattedYesterday


class AsteroidDataRepository(
    private val asteroidLocalDataSource: AsteroidLocalDataSource,
    private val asteroidRemoteDataSource: AsteroidRemoteDataSource,
    private val asteroidRemoteMapper: AsteroidRemoteMapper,
    private val asteroidLocalMapper: AsteroidLocalMapper,
) : AsteroidRepository {

    override suspend fun saveAsteroids(): Boolean {
        val asteroidEntities = arrayListOf<AsteroidEntity>()
        val asteroidsByDate = asteroidRemoteDataSource.getAsteroids()
        asteroidsByDate.forEach { (date, asteroids) ->
            val processedAsteroids = asteroidRemoteMapper.map(asteroids)
            processedAsteroids.forEach { it.appearanceDate = date }
            asteroidEntities.addAll(processedAsteroids)
        }
        return asteroidLocalDataSource.insertAll(asteroidEntities) == asteroidEntities.count()
    }

    override fun getAsteroids(): LiveData<List<Asteroid>> {
        return Transformations.map(asteroidLocalDataSource.retrieveAll()) {
            asteroidLocalMapper.map(it)
        }
    }

    override suspend fun cleanStaleAsteroids(): Boolean {
        asteroidLocalDataSource.retrieveFromBefore(formattedYesterday).apply {
            return asteroidLocalDataSource.delete(this) == this.count()
        }
    }

}