package com.udacity.asteroidradar.platform.background.picture_of_the_day

import android.content.Context
import androidx.work.*
import com.udacity.asteroidradar.domain.use_case.picture_of_the_day.SavePictureOfTheDayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit


class PictureOfTheDaySyncWorkManager(
    var context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams), KoinComponent {

    private val savePictureOfTheDayUseCase: SavePictureOfTheDayUseCase by inject()

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Job() + Dispatchers.IO) {
            try{
                if(savePictureOfTheDayUseCase()) {
                    Result.success()
                } else {
                    Result.failure()
                }
            } catch (e: Exception) {
                Timber.e(e, "Error getting new picture of the day")
                Result.failure()
            }
        }
    }

    companion object {
        fun periodicWorkRequest() = PeriodicWorkRequest.Builder(
            PictureOfTheDaySyncWorkManager::class.java, REPEAT_INTERVAL, TimeUnit.HOURS, FLEX_INTERVAL, TimeUnit.HOURS)
            .addTag(TAG_WORKER_PICTURE_OF_THE_DAY_SYNC)
            .setConstraints(SYNC_CONSTRAINTS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .build()

        const val TAG_WORKER_PICTURE_OF_THE_DAY_SYNC = "SyncPictureOfTheDayWorkManager"
        private const val REPEAT_INTERVAL = 24L
        private const val FLEX_INTERVAL = 1L

        private val SYNC_CONSTRAINTS = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }

}