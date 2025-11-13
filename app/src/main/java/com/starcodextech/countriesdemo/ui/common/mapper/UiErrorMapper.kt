package com.starcodextech.countriesdemo.ui.common.mapper

import androidx.annotation.StringRes
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.error.UiError

fun AppError.toUiError(
    @StringRes networkError: Int? = null,
    @StringRes notFoundError: Int? = null,
    @StringRes genericError: Int? = null
): UiError = when (this) {
    AppError.Network -> {
        UiError.Network(networkError ?: R.string.ui_network_error)
    }
    AppError.NotFound -> {
        UiError.NotFound(notFoundError ?: R.string.ui_not_found_error)
    }
    else -> {
        UiError.Generic(genericError ?: R.string.ui_generic_error)
    }
}