package com.udacity.asteroidradar.utils.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime


@Entity(
    tableName = PictureOfTheDayEntity.TABLE_NAME,
    indices = [
        Index(value = [PictureOfTheDayEntity.FIELD_ID])
    ]
)
data class PictureOfTheDayEntity(
    @ColumnInfo(name = FIELD_ID)
    @PrimaryKey
    val date: String,
    @ColumnInfo(name = FIELD_COPYRIGHT)
    val copyright: String?,
    @ColumnInfo(name = FIELD_EXPLANATION)
    val explanation: String,
    @ColumnInfo(name = FIELD_MEDIA_TYPE)
    val mediaType: String,
    @ColumnInfo(name = FIELD_TITLE)
    val title: String,
    @ColumnInfo(name = FIELD_URL)
    val url: String,
    @ColumnInfo(name = FIELD_CREATED_AT)
    var createdAt: OffsetDateTime,
    @ColumnInfo(name = FIELD_UPDATED_AT)
    var updatedAt: OffsetDateTime?
) {

    companion object {
        internal const val TABLE_NAME = "pictureOfTheDay"

        internal const val FIELD_ID = "id"
        internal const val FIELD_COPYRIGHT = "copyright"
        internal const val FIELD_EXPLANATION = "explanation"
        internal const val FIELD_MEDIA_TYPE = "mediaType"
        internal const val FIELD_TITLE = "title"
        internal const val FIELD_URL = "url"
        internal const val FIELD_CREATED_AT = "createdAt"
        internal const val FIELD_UPDATED_AT = "updatedAt"
    }

}