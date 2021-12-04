package com.udacity.asteroidradar.data.datasource.remote

import com.udacity.asteroidradar.data.datasource.remote.api.PictureOfTheDayApi
import com.udacity.asteroidradar.data.datasource.remote.dto.pictureOfTheDay.PictureOfTheDayResponse
import com.udacity.asteroidradar.utils.network.safeApiCall


class PictureOfTheDayRemoteDataSource(private val api: PictureOfTheDayApi) {

    suspend fun getPictureOfTheDay(): PictureOfTheDayResponse {
        val response = safeApiCall {
            api.getPictureOfTheDay()
        }
        return response!!
    }

}