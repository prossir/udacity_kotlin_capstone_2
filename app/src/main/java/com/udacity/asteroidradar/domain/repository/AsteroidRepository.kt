package com.udacity.asteroidradar.domain.repository

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.domain.entity.Asteroid


interface AsteroidRepository {

    suspend fun saveAsteroids() : Boolean
    fun getAsteroidsFilteredBy(liveFilter: LiveData<Int?>) : LiveData<List<Asteroid>>
    suspend fun cleanStaleAsteroids(): Boolean

}