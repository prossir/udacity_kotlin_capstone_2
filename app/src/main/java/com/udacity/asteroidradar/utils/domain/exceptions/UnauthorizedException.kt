package com.udacity.asteroidradar.utils.domain.exceptions

import java.io.IOException


class UnauthorizedException(
    message: String? = null,
    cause: Throwable? = null
) : IOException(message ?: cause?.message, cause)
