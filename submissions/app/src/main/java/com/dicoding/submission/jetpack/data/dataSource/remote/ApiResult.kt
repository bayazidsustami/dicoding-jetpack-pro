package com.dicoding.submission.jetpack.data.dataSource.remote

sealed class ApiResult<out T>{
    data class Success<T>(val data: T): ApiResult<T>()
    data class Error<T>(val data: T?= null, val message: String? = null): ApiResult<T>()
}
