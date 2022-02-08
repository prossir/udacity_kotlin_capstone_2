package com.udacity.asteroidradar.utils.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.udacity.asteroidradar.utils.converters.DateTimeProvider
import com.udacity.asteroidradar.utils.converters.di.NAMED_OFFSET_PROVIDER
import com.udacity.asteroidradar.utils.db.models.AsteroidEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.threeten.bp.OffsetDateTime


@Dao
abstract class AsteroidDao : BaseDao<AsteroidEntity>, KoinComponent {

    private val dateTimeProvider: DateTimeProvider<OffsetDateTime> by inject(named(NAMED_OFFSET_PROVIDER))

    @Transaction
    open fun insertOrUpdate(entity: AsteroidEntity) {
        val now = dateTimeProvider.now()
        val item = findById(entity.id)
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

    @Query("SELECT * FROM asteroid WHERE id = :id LIMIT 1")
    abstract fun findById(id: String): AsteroidEntity?

    @Query("SELECT * FROM asteroid WHERE appearance_date < :date")
    abstract fun findFromBefore(date: String): List<AsteroidEntity>

    @Query("SELECT * FROM asteroid ORDER BY created_at DESC")
    abstract fun retrieveAll(): LiveData<List<AsteroidEntity>>

}