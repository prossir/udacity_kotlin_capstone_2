package com.udacity.asteroidradar.data.datasource.remote.api.di

import com.udacity.asteroidradar.data.datasource.remote.api.AsteroidsApi
import com.udacity.asteroidradar.data.datasource.remote.api.PictureOfTheDayApi
import com.udacity.asteroidradar.utils.network.di.NAMED_API_ASTEROIDS
import com.udacity.asteroidradar.utils.network.di.NAMED_API_PICTURE_OF_THE_DAY
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


val networkModule = module {
    single { providePictureOfTheDayApi(get(named(NAMED_API_PICTURE_OF_THE_DAY))) }
    single { provideAsteroidsApi(get(named(NAMED_API_ASTEROIDS))) }
}

private fun providePictureOfTheDayApi(retrofit: Retrofit): PictureOfTheDayApi {
    return retrofit.create(PictureOfTheDayApi::class.java)
}

private fun provideAsteroidsApi(retrofit: Retrofit): AsteroidsApi {
    return retrofit.create(AsteroidsApi::class.java)
}