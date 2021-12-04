package com.udacity.asteroidradar.utils.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(objects: List<T>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg obj: T)

    @Delete
    fun delete(objects: List<T>): Int

}