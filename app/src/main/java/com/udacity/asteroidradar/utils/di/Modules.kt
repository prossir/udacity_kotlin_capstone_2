package com.udacity.asteroidradar.utils.di

import com.udacity.asteroidradar.utils.converters.di.coreProvideModule
import com.udacity.asteroidradar.utils.db.di.coreDatabaseModule
import com.udacity.asteroidradar.utils.extensions.listByElementsOf
import com.udacity.asteroidradar.utils.network.di.coreNetworkModule
import org.koin.core.module.Module


val utilsModules by lazy {
    listByElementsOf<Module>(
        coreProvideModule,
        coreNetworkModule,
        coreDatabaseModule
    )
}