package com.virginmoney.connectivity

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T?): ResultWrapper<T>()
    data class Error(val code: Int? = null, val exception: Throwable): ResultWrapper<Nothing>()
}