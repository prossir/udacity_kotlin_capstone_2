package com.udacity.asteroidradar.data.di

import com.udacity.asteroidradar.data.datasource.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.data.datasource.local.PictureOfTheDayLocalDataSource
import com.udacity.asteroidradar.data.datasource.remote.AsteroidRemoteDataSource
import com.udacity.asteroidradar.data.datasource.remote.PictureOfTheDayRemoteDataSource
import com.udacity.asteroidradar.data.datasource.remote.api.di.networkModule
import com.udacity.asteroidradar.data.mapper.AsteroidLocalMapper
import com.udacity.asteroidradar.data.mapper.AsteroidRemoteMapper
import com.udacity.asteroidradar.data.mapper.PictureOfTheDayLocalMapper
import com.udacity.asteroidradar.data.mapper.PictureOfTheDayRemoteMapper
import com.udacity.asteroidradar.data.repository.AsteroidDataRepository
import com.udacity.asteroidradar.data.repository.PictureOfTheDayDataRepository
import com.udacity.asteroidradar.domain.repository.AsteroidRepository
import com.udacity.asteroidradar.domain.repository.PictureOfTheDayRepository
import com.udacity.asteroidradar.utils.extensions.listByElementsOf
import org.koin.core.module.Module
import org.koin.dsl.module


val dataModules by lazy {
    listByElementsOf<Module>(
        networkModule,
        repositoriesModules
    )
}

internal val repositoriesModules by lazy {
    listByElementsOf<Module>(
        asteroidDataModule
    )
}

internal val asteroidDataModule = module {

    factory { PictureOfTheDayLocalMapper() }
    factory { PictureOfTheDayRemoteMapper() }
    single { PictureOfTheDayRemoteDataSource(get()) }
    single { PictureOfTheDayLocalDataSource(get()) }
    single<PictureOfTheDayRepository> { PictureOfTheDayDataRepository(get(), get(), get(), get()) }

    factory { AsteroidLocalMapper() }
    factory { AsteroidRemoteMapper() }
    single { AsteroidRemoteDataSource(get()) }
    single { AsteroidLocalDataSource(get()) }
    single<AsteroidRepository> { AsteroidDataRepository(get(), get(), get(), get()) }

}