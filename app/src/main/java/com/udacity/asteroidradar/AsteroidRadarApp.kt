package com.udacity.asteroidradar

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.udacity.asteroidradar.di.injectKoinModules
import com.udacity.asteroidradar.platform.background.WorkManagerAdmin
import timber.log.Timber


class AsteroidRadarApp: Application() {

    override fun onCreate() {
        super.onCreate()
        injectKoinModules(this)
        Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
        WorkManagerAdmin.initWorkManagers()
    }

}