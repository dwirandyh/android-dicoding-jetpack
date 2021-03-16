package com.dwirandyh.jetpack.external

import kotlin.Exception

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure<out T : Exception>(val throwable: Throwable) : Result<T>()
    object Loading: Result<Nothing>()
}
