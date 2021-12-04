package com.udacity.asteroidradar.domain.repository

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.domain.entity.Asteroid


interface AsteroidRepository {

    suspend fun saveAsteroids() : Boolean
    fun getAsteroids() : LiveData<List<Asteroid>>
    suspend fun cleanStaleAsteroids(): Boolean

}