package com.starcodextech.countriesdemo.data.error

import com.starcodextech.countriesdemo.common.error.AppError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toAppError(): AppError = when (this) {
    is IOException -> AppError.Network
    is HttpException -> when (this.code()) {
        401, 403 -> AppError.Unauthorized
        404      -> AppError.NotFound
        in 500..599 -> AppError.ServerError
        else    -> AppError.Unknown(message ?: "HTTP error ${this.code()}")
    }
    else -> AppError.Unknown(message)
}