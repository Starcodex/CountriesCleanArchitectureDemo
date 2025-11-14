package com.starcodextech.countriesdemo.data.remote.mapper

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import javax.inject.Inject

class CountryDetailsMapper @Inject constructor() : Mapper<CountryDto, CountryDetails> {
    override fun map(from: CountryDto): CountryDetails =
        CountryDetails(
            flagUrl = from.flags?.png.orEmpty(),
            commonName = from.name?.common.orEmpty(),
            officialName = from.name?.official.orEmpty(),
            capital = formattedCapital(from.capital),
            region = from.region.orEmpty(),
            subRegion = from.subregion.orEmpty(),
            languages = from.languages?.values?.joinToString(", ").orEmpty(),
            currencies = formattedCurrency(from.currencies),
            population = from.population.toString(),
            carDriverSide = from.car?.side.orEmpty()
        )

}