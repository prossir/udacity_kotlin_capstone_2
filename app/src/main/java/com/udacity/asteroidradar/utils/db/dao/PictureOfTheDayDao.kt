package com.udacity.asteroidradar.utils.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.udacity.asteroidradar.utils.converters.DateTimeProvider
import com.udacity.asteroidradar.utils.converters.di.NAMED_OFFSET_PROVIDER
import com.udacity.asteroidradar.utils.db.models.PictureOfTheDayEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.threeten.bp.OffsetDateTime


@Dao
abstract class PictureOfTheDayDao : BaseDao<PictureOfTheDayEntity>, KoinComponent {

    private val dateTimeProvider: DateTimeProvider<OffsetDateTime> by inject(named(NAMED_OFFSET_PROVIDER))

    @Transaction
    open fun insertOrUpdate(entity: PictureOfTheDayEntity) {
        val now = dateTimeProvider.now()
        val item = findByDate(entity.date)
        if (item == null) {
            insert(entity.apply {
                createdAt = now
                updatedAt = now
            })
        } else {
            update(entity.apply {
                createdAt = item.createdAt
                updatedAt = now
            })
        }
    }

    @Query("SELECT * FROM pictureOfTheDay WHERE id = :date")
    abstract fun findByDate(date: String): PictureOfTheDayEntity?

    @Query("SELECT * FROM pictureOfTheDay WHERE id < :date")
    abstract fun findFromBefore(date: String): List<PictureOfTheDayEntity>

    @Query("SELECT * FROM pictureOfTheDay WHERE id = :date LIMIT 1")
    abstract fun findLiveByDate(date: String): LiveData<PictureOfTheDayEntity?>

}