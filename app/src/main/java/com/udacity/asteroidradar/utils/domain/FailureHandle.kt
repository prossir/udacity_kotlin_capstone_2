package com.udacity.asteroidradar.utils.domain

import com.udacity.asteroidradar.utils.domain.exceptions.NetworkConnectionException
import com.udacity.asteroidradar.utils.domain.exceptions.ServerErrorException
import com.udacity.asteroidradar.utils.domain.exceptions.UnauthorizedException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class FailureHandle {

    fun build(e: Throwable): Throwable {
        return when (e) {
            is HttpException -> buildExceptionByType(e)
            is SocketTimeoutException -> e
            is IOException -> NetworkConnectionException(cause = e)
            // Controlled custom error
            else -> ServerErrorException(message = e.message, cause = e)
        }
    }

    private fun buildExceptionByType(e: HttpException): Exception {
        return when (e.code()) {
            UNAUTHORIZED_CODE, FORBIDDEN_CODE -> UnauthorizedException(cause = e)
            else -> ServerErrorException(cause = e)
        }
    }

    companion object {
        const val UNAUTHORIZED_CODE = 401
        const val FORBIDDEN_CODE = 403
    }

}