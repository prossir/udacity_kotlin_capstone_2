package com.udacity.asteroidradar.utils.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class FailureModel(
    @StringRes val title: Int = NONE,
    @StringRes val commonMessage: Int = NONE,
    @DrawableRes val icon: Int = NONE,
    val code: Int = NONE,
    val exactMessage: String? = EMPTY_STRING,
    val origin : Throwable
) {

    companion object {
        const val NONE = -1
        const val EMPTY_STRING = ""
    }

}