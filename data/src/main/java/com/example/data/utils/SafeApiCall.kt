package com.example.data.utils

import com.example.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Flow<ResultWrapper<T>> = flow {
    emit(ResultWrapper.Loading)
    val result = apiCall.invoke()
    emit(ResultWrapper.Success(result))
}.catch { error ->

    emit(ResultWrapper.Error(error.message ?: "Error Occurred, Try again later"))
}