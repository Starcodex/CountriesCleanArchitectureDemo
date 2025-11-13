package com.starcodextech.countriesdemo.ui.countries.list.state

import com.starcodextech.countriesdemo.common.error.UiError
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel

data class CountriesListUiState(
    val isLoading: Boolean = false,
    val countries: List<CountrySummaryUiModel> = emptyList(),
    val error: UiError? = null
)