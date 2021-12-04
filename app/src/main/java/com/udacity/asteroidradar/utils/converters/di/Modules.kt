package com.udacity.asteroidradar.utils.converters.di

import com.udacity.asteroidradar.utils.converters.DateTimeProvider
import com.udacity.asteroidradar.utils.converters.OffsetDateTimeProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.OffsetDateTime


const val NAMED_OFFSET_PROVIDER = "OffsetDateTimeProvider"

internal val coreProvideModule = module {
    single<DateTimeProvider<OffsetDateTime>>(named(NAMED_OFFSET_PROVIDER)) { OffsetDateTimeProvider() }
}