package com.udacity.asteroidradar.utils.extensions

import com.udacity.asteroidradar.utils.domain.FailureHandle
import retrofit2.Response


internal fun <T : Any> Response<T>.buildSuccess() = SuccessHandle<T>().build(this.body())

internal fun Throwable.buildFailure() = FailureHandle().build(this)
