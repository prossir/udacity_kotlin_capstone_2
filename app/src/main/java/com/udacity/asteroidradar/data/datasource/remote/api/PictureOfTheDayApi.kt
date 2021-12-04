package com.udacity.asteroidradar.data.datasource.remote.api

import com.udacity.asteroidradar.data.datasource.remote.dto.pictureOfTheDay.PictureOfTheDayResponse
import retrofit2.Response
import retrofit2.http.GET


interface PictureOfTheDayApi {

    @GET("apod")
    suspend fun getPictureOfTheDay() : Response<PictureOfTheDayResponse>

}