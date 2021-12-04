package com.udacity.asteroidradar.utils.domain.exceptions


class ServerErrorException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message ?: cause?.message, cause)
