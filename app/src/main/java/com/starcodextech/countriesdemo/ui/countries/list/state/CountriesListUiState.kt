package com.starcodextech.countriesdemo.ui.countries.list.state

import com.starcodextech.countriesdemo.common.error.UiError
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel

sealed class CountriesListUiState {

    data object Loading : CountriesListUiState()

    data class Error(
        val uiError: UiError
    ) : CountriesListUiState()

    data object Empty : CountriesListUiState()
    data class Success(
        val content: SuccessContent
    ) : CountriesListUiState() {

        sealed class SuccessContent {
            data class WithData(
                val countries: List<CountrySummaryUiModel>
            ) : SuccessContent()

            data object NoData : SuccessContent()
        }
    }
}