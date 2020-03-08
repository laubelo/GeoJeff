package com.app.geojeff.data.repository.remote

sealed class DataState<out T : Any> {
    class Success<out T : Any>(val data: T) : DataState<T>()
    class Error(val exception: Throwable) : DataState<Nothing>()
}