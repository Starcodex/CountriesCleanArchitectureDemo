package com.starcodextech.countriesdemo.ui.countries.detail.mapper

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.ui.countries.detail.model.CountryDetailsUiModel
import javax.inject.Inject

class CountryDetailsUiMapper @Inject constructor() :
    Mapper<CountryDetails, CountryDetailsUiModel> {
    override fun map(from: CountryDetails): CountryDetailsUiModel =
        CountryDetailsUiModel(
            flagUrl = from.flagUrl,
            commonName = from.commonName,
            officialName = from.officialName,
            capital = from.capital,
            region = from.region,
            subRegion = from.subRegion,
            languages = from.languages,
            currencies = from.currencies,
            population = from.population,
            carDriverSide = from.carDriverSide
        )
}