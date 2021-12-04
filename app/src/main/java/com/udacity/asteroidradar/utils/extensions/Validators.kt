package com.udacity.asteroidradar.utils.extensions

import android.util.Patterns


fun String.isValidEmail() =
    this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()