package com.starcodextech.countriesdemo.ui.countries.list.state

import com.starcodextech.countriesdemo.common.error.UiError
import com.starcodextech.countriesdemo.ui.common.state.UiState
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel

data class CountriesListUiState(
    override val isLoading: Boolean = false,
    override val error: UiError? = null,
    val countries: List<CountrySummaryUiModel> = emptyList()
) : UiState