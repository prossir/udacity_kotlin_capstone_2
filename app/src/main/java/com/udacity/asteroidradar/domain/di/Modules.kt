package com.udacity.asteroidradar.domain.di

import com.udacity.asteroidradar.domain.use_case.di.asteroidUseCasesModule
import com.udacity.asteroidradar.utils.extensions.listByElementsOf
import org.koin.core.module.Module


internal val domainModules by lazy {
    listByElementsOf<Module>(
        useCasesModules
    )
}

private val useCasesModules by lazy {
    listOf(
        asteroidUseCasesModule,
    )
}