package com.udacity.asteroidradar.domain.use_case.di

import com.udacity.asteroidradar.domain.use_case.asteroid.CleanStaleAsteroidsUseCase
import com.udacity.asteroidradar.domain.use_case.asteroid.GetAsteroidsUseCase
import com.udacity.asteroidradar.domain.use_case.asteroid.SaveAsteroidsUseCase
import com.udacity.asteroidradar.domain.use_case.picture_of_the_day.CleanStalePictureOfTheDayUseCase
import com.udacity.asteroidradar.domain.use_case.picture_of_the_day.GetPictureOfTheDayUseCase
import com.udacity.asteroidradar.domain.use_case.picture_of_the_day.SavePictureOfTheDayUseCase
import org.koin.dsl.module


internal val asteroidUseCasesModule = module {

    factory { CleanStaleAsteroidsUseCase(get()) }
    factory { GetAsteroidsUseCase(get()) }
    factory { SaveAsteroidsUseCase(get()) }

    factory { CleanStalePictureOfTheDayUseCase(get()) }
    factory { GetPictureOfTheDayUseCase(get()) }
    factory { SavePictureOfTheDayUseCase(get()) }

}