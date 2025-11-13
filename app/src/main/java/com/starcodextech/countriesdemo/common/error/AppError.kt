package com.starcodextech.countriesdemo.common.error

sealed class AppError {
    object Network: AppError()
    object NotFound: AppError()
    object Unauthorized : AppError()
    object ServerError : AppError()
    data class Unknown(val message: String? = null) : AppError()
}