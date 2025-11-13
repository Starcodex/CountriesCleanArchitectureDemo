package com.starcodextech.countriesdemo.data.remote.mapper

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import javax.inject.Inject


class CountrySummaryMapper @Inject constructor(): Mapper<CountryDto, CountrySummary> {
    override fun map(from: CountryDto): CountrySummary =
        CountrySummary(
            code = from.cca3,
            flagUrl = from.flags.png,
            commonName = from.name.common,
            officialName = from.name.official,
            capital = from.capital.firstOrNull() ?: "N/A",
        )
}