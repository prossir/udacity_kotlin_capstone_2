package com.udacity.asteroidradar.data.datasource.remote.api

import com.udacity.asteroidradar.data.datasource.remote.dto.asteroid.AsteroidsResponseHolder
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AsteroidsApi {

    @GET("feed")
    suspend fun getAsteroids(@Query("start_date") startDate:String, @Query("end_date") endDate:String) : Response<AsteroidsResponseHolder>

}