package com.udacity.asteroidradar.platform.background

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.udacity.asteroidradar.platform.background.asteroids.AsteroidCleanUpWorkManager
import com.udacity.asteroidradar.platform.background.asteroids.AsteroidSyncWorkManager
import com.udacity.asteroidradar.platform.background.picture_of_the_day.PictureOfTheDayCleanUpWorkManager
import com.udacity.asteroidradar.platform.background.picture_of_the_day.PictureOfTheDaySyncWorkManager


class WorkManagerAdmin {

    companion object {
        fun initWorkManagers(applicationContext: Application) {
            WorkManager.getInstance(applicationContext).apply {
                // Asteroids related
                this.enqueueUniquePeriodicWork(
                    AsteroidSyncWorkManager.TAG_WORKER_ASTEROID_SYNC,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    AsteroidSyncWorkManager.periodicWorkRequest())

                this.enqueueUniquePeriodicWork(
                    AsteroidCleanUpWorkManager.TAG_WORKER_ASTEROIDS_CLEAN_UP,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    AsteroidCleanUpWorkManager.periodicTimeWorkRequest())

                // Picture of the day related
                this.enqueueUniquePeriodicWork(
                    PictureOfTheDaySyncWorkManager.TAG_WORKER_PICTURE_OF_THE_DAY_SYNC,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    PictureOfTheDaySyncWorkManager.periodicWorkRequest())

                this.enqueueUniquePeriodicWork(
                    PictureOfTheDayCleanUpWorkManager.TAG_WORKER_PICTURE_OF_THE_DAY_CLEAN_UP,
                    ExistingPeriodicWorkPolicy.REPLACE,
                    PictureOfTheDayCleanUpWorkManager.periodicTimeWorkRequest())

                // Initial work during application first launch
                this.beginUniqueWork(
                    INITIAL_SETUP_WORK,
                    ExistingWorkPolicy.REPLACE,
                    PictureOfTheDaySyncWorkManager.initialWorkRequest())
                    .then(AsteroidSyncWorkManager.initialWorkRequest())
                    .enqueue()
            }
        }

        private const val INITIAL_SETUP_WORK = "initial_setup_work"

    }

}