package com.starcodextech.countriesdemo.ui.countries.detail.state

import com.starcodextech.countriesdemo.common.error.UiError
import com.starcodextech.countriesdemo.ui.countries.detail.model.CountryDetailsUiModel

data class CountryDetailsUiState(
    val isLoading: Boolean = false,
    val country: CountryDetailsUiModel? = null,
    val error: UiError? = null
)