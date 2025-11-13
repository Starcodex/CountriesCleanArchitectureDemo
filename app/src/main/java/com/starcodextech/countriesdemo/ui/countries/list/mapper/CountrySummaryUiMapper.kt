package com.starcodextech.countriesdemo.ui.countries.list.mapper

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import javax.inject.Inject

class CountrySummaryUiMapper @Inject constructor() :
    Mapper<CountrySummary, CountrySummaryUiModel> {

    override fun map(from: CountrySummary): CountrySummaryUiModel =
        CountrySummaryUiModel(
            code = from.code,
            commonName = from.commonName,
            officialName = from.officialName,
            capital = from.capital,
            flagUrl = from.flagUrl
        )
}