package com.starcodextech.countriesdemo.ui.countries.list.state

import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel

sealed class CountriesListSuccess {
    data class WithData(
        val countries: List<CountrySummaryUiModel>
    ) : CountriesListSuccess()

    data object NoData : CountriesListSuccess()
}