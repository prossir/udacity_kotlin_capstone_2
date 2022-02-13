package com.udacity.asteroidradar.utils.extensions

import com.udacity.asteroidradar.utils.Constants
import java.text.SimpleDateFormat
import java.util.*


private fun getFormattedDayFrom(daysToAdd: Int): String {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, daysToAdd)
    val plusSevenDaysFromToday = calendar.time
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(plusSevenDaysFromToday)
}

val yesterdayDate = getFormattedDayFrom(Constants.YESTERDAY_FROM_TODAY)
val todayDate = getFormattedDayFrom(Constants.TODAY_FROM_TODAY)
val followingWeekFromTodayDate = getFormattedDayFrom(Constants.FOLLOWING_WEEK_FROM_TODAY)
val previousWeekFromTodayDate = getFormattedDayFrom(Constants.PREVIOUS_WEEK_FROM_TODAY)