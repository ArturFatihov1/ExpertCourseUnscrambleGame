package com.example.expertcourseunscramblegame.load.data.cloud

import com.example.expertcourseunscramblegame.R
import com.example.expertcourseunscramblegame.load.data.NoInternetConnectionException
import com.example.expertcourseunscramblegame.load.data.ServiceUnavailable
import java.io.IOException

interface HandleError<T> {

    fun handle(error: Exception): T

    class DataToDomain : HandleError<Exception> {

        override fun handle(error: Exception): Exception {
            return when (error) {
                is IOException -> NoInternetConnectionException()
                else -> ServiceUnavailable()
            }
        }
    }

    class DomainToUi : HandleError<Int> {

        override fun handle(error: Exception): Int {
            return when (error) {
                is NoInternetConnectionException -> R.string.no_internet_connection
                else -> R.string.service_unavailable
            }
        }
    }
}