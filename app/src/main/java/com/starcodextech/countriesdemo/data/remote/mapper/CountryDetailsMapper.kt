package com.starcodextech.countriesdemo.data.remote.mapper

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails

class CountryDetailsMapper : Mapper<CountryDto, CountryDetails> {
    override fun map(from: CountryDto): CountryDetails =
        CountryDetails(
            flagUrl = from.flags.png,
            commonName = from.name.common,
            officialName = from.name.official,
            capital = from.capital.firstOrNull().orEmpty(),
            region = from.region,
            subRegion = from.subregion.orEmpty(),
            languages = from.languages.values.joinToString(", "),
            currencies = formattedCurrency(from.currencies),
            population = from.population.toString(),
            carDriverSide = from.car.side
        )

}