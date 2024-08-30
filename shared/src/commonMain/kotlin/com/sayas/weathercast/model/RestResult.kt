package com.sayas.weathercast.model

sealed class RestResult<out T> {

    data class Success<out T>(val data: T) : RestResult<T>()

    data class Error(val message: String) : RestResult<Nothing>()
}