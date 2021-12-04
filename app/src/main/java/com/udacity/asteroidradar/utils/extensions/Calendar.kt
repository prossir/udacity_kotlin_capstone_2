package com.udacity.asteroidradar.utils.extensions

import com.udacity.asteroidradar.utils.Constants
import java.text.SimpleDateFormat
import java.util.*


val formattedYesterday: String
    get() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val plusSevenDaysFromToday = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(plusSevenDaysFromToday)
    }

val formattedToday: String
    get() {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(currentTime)
    }

val formattedSevenDaysFromToday: String
    get() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 7)
        val plusSevenDaysFromToday = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(plusSevenDaysFromToday)
    }
