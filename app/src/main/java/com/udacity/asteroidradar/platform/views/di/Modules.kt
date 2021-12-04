package com.udacity.asteroidradar.platform.views.di

import com.udacity.asteroidradar.platform.views.asteroid_detail.di.asteroidDetailFeatureModule
import com.udacity.asteroidradar.platform.views.common.di.commonFeatureModule
import com.udacity.asteroidradar.utils.extensions.listByElementsOf
import org.koin.core.module.Module


val featuresModules by lazy {
    listByElementsOf<Module>(
        commonFeatureModule,
        asteroidDetailFeatureModule
    )
}