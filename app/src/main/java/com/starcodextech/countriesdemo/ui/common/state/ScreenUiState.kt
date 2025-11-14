package com.starcodextech.countriesdemo.ui.common.state

import com.starcodextech.countriesdemo.common.error.UiError

sealed class ScreenUiState<out T> {

    data object Loading : ScreenUiState<Nothing>()

    data class Error(val uiError: UiError) : ScreenUiState<Nothing>()

    data object Empty : ScreenUiState<Nothing>()

    data class Success<T>(val content: T) : ScreenUiState<T>()
}