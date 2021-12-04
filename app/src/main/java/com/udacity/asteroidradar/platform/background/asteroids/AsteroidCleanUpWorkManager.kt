package com.udacity.asteroidradar.platform.background.asteroids

import android.content.Context
import androidx.work.*
import com.udacity.asteroidradar.domain.use_case.asteroid.CleanStaleAsteroidsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit


class AsteroidCleanUpWorkManager(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters), KoinComponent {

    private val cleanStaleAsteroidsUseCase: CleanStaleAsteroidsUseCase by inject()

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Job() + Dispatchers.IO) {
            try{
                if(cleanStaleAsteroidsUseCase()) {
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
        fun periodicTimeWorkRequest() = PeriodicWorkRequest.Builder(
            AsteroidCleanUpWorkManager::class.java, REPEAT_INTERVAL, TimeUnit.HOURS, FLEX_INTERVAL, TimeUnit.HOURS)
            .addTag(TAG_WORKER_ASTEROIDS_CLEAN_UP)
            .setConstraints(SYNC_CONSTRAINTS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .build()

        const val TAG_WORKER_ASTEROIDS_CLEAN_UP = "CleanUpAsteroidsWorkManager"
        private const val REPEAT_INTERVAL = 24L
        private const val FLEX_INTERVAL = 1L

        private val SYNC_CONSTRAINTS = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
    }

}