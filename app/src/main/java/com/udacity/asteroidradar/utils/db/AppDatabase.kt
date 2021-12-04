package com.udacity.asteroidradar.utils.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.udacity.asteroidradar.utils.db.dao.AsteroidDao
import com.udacity.asteroidradar.utils.db.dao.PictureOfTheDayDao
import com.udacity.asteroidradar.utils.db.models.AsteroidEntity
import com.udacity.asteroidradar.utils.db.models.PictureOfTheDayEntity
import paolo.udacity.color.my.shoestoreinventorycapstone_1.utils.db.converter.OffsetDateTimeConverter


@Database(
    entities = [
        AsteroidEntity::class,
        PictureOfTheDayEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(OffsetDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun asteroidDao(): AsteroidDao
    abstract fun pictureOfTheDayDao(): PictureOfTheDayDao

    companion object {
        const val VERSION = 1
        const val NAME = "db"
    }

}