package com.starcodextech.countriesdemo.ui.common.state

import com.starcodextech.countriesdemo.common.error.UiError

interface UiState {
    val isLoading: Boolean
    val error: UiError?
}