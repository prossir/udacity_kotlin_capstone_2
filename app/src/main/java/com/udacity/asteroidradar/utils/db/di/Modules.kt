package com.udacity.asteroidradar.utils.db.di

import com.udacity.asteroidradar.utils.db.provider.DaoProvider
import com.udacity.asteroidradar.utils.db.provider.DatabaseProvider
import org.koin.dsl.module


internal val coreDatabaseModule = module {
    single { DatabaseProvider(get()) }
    single { DaoProvider(get()) }
}