package com.udacity.asteroidradar.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.udacity.asteroidradar.data.datasource.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.data.datasource.remote.AsteroidRemoteDataSource
import com.udacity.asteroidradar.data.mapper.AsteroidLocalMapper
import com.udacity.asteroidradar.data.mapper.AsteroidRemoteMapper
import com.udacity.asteroidradar.domain.entity.Asteroid
import com.udacity.asteroidradar.domain.repository.AsteroidRepository
import com.udacity.asteroidradar.platform.views.list_asteroids.AsteroidFilterEnum
import com.udacity.asteroidradar.utils.db.models.AsteroidEntity
import com.udacity.asteroidradar.utils.extensions.previousWeekFromTodayDate
import com.udacity.asteroidradar.utils.extensions.todayDate
import com.udacity.asteroidradar.utils.extensions.yesterdayDate


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

    override fun getAsteroidsFilteredBy(liveFilter: LiveData<Int?>): LiveData<List<Asteroid>> {
        return Transformations.switchMap(liveFilter) { filter ->
            val source: LiveData<List<Asteroid>>
            when(filter) {
                AsteroidFilterEnum.DAILY.type -> {
                    source = asteroidLocalDataSource.retrieveLiveFromDay(todayDate).map {
                        asteroidLocalMapper.map(it)
                    }
                }
                AsteroidFilterEnum.WEEKLY.type -> {
                    source = asteroidLocalDataSource.retrieveLiveFromAfter(previousWeekFromTodayDate).map {
                        asteroidLocalMapper.map(it)
                    }
                }
                else -> {
                    source = asteroidLocalDataSource.retrieveAllLive().map {
                        asteroidLocalMapper.map(it)
                    }
                }

            }
            return@switchMap source
        }
    }

    override suspend fun cleanStaleAsteroids(): Boolean {
        asteroidLocalDataSource.retrieveFromBefore(yesterdayDate).apply {
            return asteroidLocalDataSource.delete(this) == this.count()
        }
    }

}