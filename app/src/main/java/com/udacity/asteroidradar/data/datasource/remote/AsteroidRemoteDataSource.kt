package com.udacity.asteroidradar.data.datasource.remote

import com.udacity.asteroidradar.data.datasource.remote.api.AsteroidsApi
import com.udacity.asteroidradar.data.datasource.remote.dto.asteroid.AsteroidResponse
import com.udacity.asteroidradar.utils.domain.exceptions.EmptyDataException
import com.udacity.asteroidradar.utils.extensions.formattedSevenDaysFromToday
import com.udacity.asteroidradar.utils.extensions.formattedToday
import com.udacity.asteroidradar.utils.network.safeApiCall


class AsteroidRemoteDataSource(private val api: AsteroidsApi) {

    suspend fun getAsteroids() : Map<String, List<AsteroidResponse>> {
        val response = safeApiCall {
            api.getAsteroids(formattedToday, formattedSevenDaysFromToday)
        }
        return response?.data ?: throw EmptyDataException()
    }

}

