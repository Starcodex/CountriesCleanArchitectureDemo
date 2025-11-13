package com.starcodextech.countriesdemo.common.error

import androidx.annotation.StringRes
import com.starcodextech.countriesdemo.R

sealed class UiError(
    @StringRes open val messageRes: Int
) {

    data class Network(
        @StringRes override val messageRes: Int = R.string.ui_network_error
    ) : UiError(messageRes)

    data class NotFound(
        @StringRes override val messageRes: Int = R.string.ui_not_found_error
    ) : UiError(messageRes)

    data class Generic(
        @StringRes override val messageRes: Int = R.string.ui_generic_error
    ) : UiError(messageRes)
}