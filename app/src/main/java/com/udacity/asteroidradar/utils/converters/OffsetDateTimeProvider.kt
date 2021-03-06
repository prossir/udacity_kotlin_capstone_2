package com.udacity.asteroidradar.utils.converters

import org.threeten.bp.*


class OffsetDateTimeProvider : DateTimeProvider<OffsetDateTime> {

    override fun now(): OffsetDateTime = OffsetDateTime.now(ZoneOffset.systemDefault())
    fun nowLocalDate(): LocalDate = now().toLocalDate()
    fun nowLocalDateTime(): LocalDateTime = now().toLocalDateTime()
    fun nowLocalTime(): LocalTime = now().toLocalTime()
    fun nowZoneOffset(): ZoneOffset = now().toOffsetTime().offset

}