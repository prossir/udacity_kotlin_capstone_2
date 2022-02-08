package com.udacity.asteroidradar.di

import android.app.Application
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.data.di.dataModules
import com.udacity.asteroidradar.domain.di.domainModules
import com.udacity.asteroidradar.platform.views.di.featuresModules
import com.udacity.asteroidradar.utils.di.utilsModules
import com.udacity.asteroidradar.utils.extensions.listByElementsOf
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module


internal fun injectKoinModules(app: Application) {
    startKoin {
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(app)
        modules(baseModules)
    }
}

private val baseModules by lazy {
    listByElementsOf<Module>(
        appModules
    )
}

val appModules by lazy {
    listByElementsOf<Module>(
        featuresModules,
        domainModules,
        dataModules,
        utilsModules
    )
}