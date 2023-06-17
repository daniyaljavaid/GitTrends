package com.dj.gittrends.common

import com.dj.gittrends.common.exceptions.HttpException
import com.dj.gittrends.common.exceptions.NoInternetException
import retrofit2.HttpException as RetrofitException
import java.io.IOException


internal suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultState<T> {
    return try {
        val response = apiCall.invoke()
        ResultState.Success(response)
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultState.Error(NoInternetException())
            is RetrofitException -> ResultState.Error(
                HttpException(
                    errorCode = throwable.code(),
                    errorMessage = throwable.message() ?: ""
                )
            )

            else -> ResultState.Error(throwable)
        }
    }
}
