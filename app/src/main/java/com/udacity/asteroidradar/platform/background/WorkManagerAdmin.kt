package com.udacity.asteroidradar.platform.background

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.udacity.asteroidradar.platform.background.asteroids.AsteroidCleanUpWorkManager
import com.udacity.asteroidradar.platform.background.asteroids.AsteroidSyncWorkManager
import com.udacity.asteroidradar.platform.background.picture_of_the_day.PictureOfTheDayCleanUpWorkManager
import com.udacity.asteroidradar.platform.background.picture_of_the_day.PictureOfTheDaySyncWorkManager


class WorkManagerAdmin {

    companion object {
        fun initWorkManagers() {
            WorkManager.getInstance().apply {
                // Asteroids related
                this.enqueueUniquePeriodicWork(
                    AsteroidSyncWorkManager.TAG_WORKER_ASTEROID_SYNC,
                    ExistingPeriodicWorkPolicy.KEEP,
                    AsteroidSyncWorkManager.periodicWorkRequest())

                this.enqueueUniquePeriodicWork(
                    AsteroidCleanUpWorkManager.TAG_WORKER_ASTEROIDS_CLEAN_UP,
                    ExistingPeriodicWorkPolicy.KEEP,
                    AsteroidCleanUpWorkManager.periodicTimeWorkRequest())

                // Picture of the day related
                this.enqueueUniquePeriodicWork(
                    PictureOfTheDaySyncWorkManager.TAG_WORKER_PICTURE_OF_THE_DAY_SYNC,
                    ExistingPeriodicWorkPolicy.KEEP,
                    PictureOfTheDaySyncWorkManager.periodicWorkRequest())

                this.enqueueUniquePeriodicWork(
                    PictureOfTheDayCleanUpWorkManager.TAG_WORKER_PICTURE_OF_THE_DAY_CLEAN_UP,
                    ExistingPeriodicWorkPolicy.KEEP,
                    PictureOfTheDayCleanUpWorkManager.periodicTimeWorkRequest())
            }
        }
    }

}