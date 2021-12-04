package com.udacity.asteroidradar.utils.network.di

import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.utils.network.createOkHttpClient
import com.udacity.asteroidradar.utils.network.createRetrofit
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


const val NAMED_API_PICTURE_OF_THE_DAY = "PICTURE_OF_THE_DAY"
const val NAMED_API_PICTURE_OF_THE_DAY_CLIENT = "PICTURE_OF_THE_DAY_CLIENT"

const val NAMED_API_ASTEROIDS = "ASTEROIDS"
const val NAMED_API_ASTEROIDS_CLIENT = "ASTEROIDS_CLIENT"

internal val coreNetworkModule = module {
    single(named(NAMED_API_PICTURE_OF_THE_DAY_CLIENT)) { createOkHttpClient() }
    single(named(NAMED_API_PICTURE_OF_THE_DAY)) { retrofitPictureOfTheDay(get(named(NAMED_API_PICTURE_OF_THE_DAY_CLIENT))) }

    single(named(NAMED_API_ASTEROIDS_CLIENT)) { createOkHttpClient() }
    single(named(NAMED_API_ASTEROIDS)) { retrofitAsteroids(get(named(NAMED_API_ASTEROIDS_CLIENT))) }
}

private fun retrofitPictureOfTheDay(okHttpClient: OkHttpClient): Retrofit {
    return createRetrofit(okHttpClient, BuildConfig.URL_PICTURE_OF_THE_DAY)
}

private fun retrofitAsteroids(okHttpClient: OkHttpClient): Retrofit {
    return createRetrofit(okHttpClient, BuildConfig.URL_ASTEROIDS)
}