package com.example.domain.utils

sealed class ResultWrapper<out T> {

    data object Loading : ResultWrapper<Nothing>()
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error(val message: String) : ResultWrapper<Nothing>()
}