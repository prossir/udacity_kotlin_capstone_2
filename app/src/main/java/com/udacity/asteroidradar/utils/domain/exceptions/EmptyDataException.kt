package com.udacity.asteroidradar.utils.domain.exceptions

class EmptyDataException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message ?: cause?.message, cause)