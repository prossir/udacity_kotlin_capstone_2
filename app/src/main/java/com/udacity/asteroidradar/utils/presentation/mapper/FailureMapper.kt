package com.udacity.asteroidradar.utils.presentation.mapper

import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.utils.domain.exceptions.NetworkConnectionException
import com.udacity.asteroidradar.utils.domain.exceptions.ServerErrorException
import com.udacity.asteroidradar.utils.mapper.SingleMapper
import com.udacity.asteroidradar.utils.presentation.model.FailureModel


abstract class FailureMapper : SingleMapper<Throwable, FailureModel>() {

    override fun map(value: Throwable) = when (value) {
        is NetworkConnectionException -> FailureModel(
            title = R.string.error_no_internet_connection_title,
            commonMessage = R.string.error_no_internet_connection_description,
            icon = R.drawable.ic_error_network,
            origin = value
        )
        is ServerErrorException -> FailureModel(
            title = R.string.error_server_request_error_title,
            commonMessage = R.string.error_server_request_error_description,
            icon = R.drawable.ic_error_info,
            exactMessage = value.localizedMessage,
            origin = value
        )
        else -> FailureModel(
            title = R.string.error_ups,
            commonMessage = R.string.error_an_error_occurred,
            exactMessage = value.message,
            icon = FailureModel.NONE,
            origin = value
        )
    }

}