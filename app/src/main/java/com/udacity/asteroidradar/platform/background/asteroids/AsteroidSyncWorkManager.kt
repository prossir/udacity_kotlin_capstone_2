package com.udacity.asteroidradar.platform.background.asteroids

import android.content.Context
import androidx.work.*
import com.udacity.asteroidradar.domain.use_case.asteroid.SaveAsteroidsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import java.util.concurrent.TimeUnit
import org.koin.core.inject
import timber.log.Timber


class AsteroidSyncWorkManager(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters), KoinComponent {

    private val saveAsteroidsUseCase: SaveAsteroidsUseCase by inject()

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Job() + Dispatchers.IO) {
            try{
                if(saveAsteroidsUseCase()) {
                    Result.success()
                } else {
                    Result.failure()
                }
            } catch (e: Exception) {
                Timber.e(e, "Error getting asteroids")
                Result.failure()
            }
        }
    }

    companion object {
        fun periodicWorkRequest() = PeriodicWorkRequest.Builder(
            AsteroidSyncWorkManager::class.java, REPEAT_INTERVAL, TimeUnit.HOURS, FLEX_INTERVAL, TimeUnit.HOURS)
            .addTag(TAG_WORKER_ASTEROID_SYNC)
            .setConstraints(SYNC_CONSTRAINTS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .build()

        const val TAG_WORKER_ASTEROID_SYNC = "SyncAsteroidsWorkManager"
        private const val REPEAT_INTERVAL = 24L
        private const val FLEX_INTERVAL = 1L

        private val SYNC_CONSTRAINTS = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }

}