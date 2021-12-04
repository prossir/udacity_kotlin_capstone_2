package com.udacity.asteroidradar.platform.views.common.di

import com.udacity.asteroidradar.platform.views.common.mapper.AsteroidMapper
import com.udacity.asteroidradar.platform.views.common.mapper.MainFailureMapper
import com.udacity.asteroidradar.platform.views.common.mapper.PictureOfTheDayMapper
import com.udacity.asteroidradar.platform.views.common.views.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


internal val commonFeatureModule = module {

    factory { AsteroidMapper() }
    factory { MainFailureMapper() }
    factory { PictureOfTheDayMapper() }

    viewModel { MainViewModel(get(), get(), get(), get()) }

}