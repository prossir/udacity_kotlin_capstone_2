package com.udacity.asteroidradar.utils.db.provider

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.utils.db.AppDatabase


class DatabaseProvider(
    private val context: Context
) {

    private var database: AppDatabase? = null

    fun getInstance(): AppDatabase =
        database ?: synchronized(this) {
            database ?: buildDatabase().also { database = it }
        }

    private fun buildDatabase(): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
            .addCallback(onCreateDatabase())
            .build()

    private fun onCreateDatabase() =
        object : RoomDatabase.Callback() {}

}