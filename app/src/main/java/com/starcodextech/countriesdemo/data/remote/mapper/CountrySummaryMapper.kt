package com.starcodextech.countriesdemo.data.remote.mapper

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import javax.inject.Inject


class CountrySummaryMapper @Inject constructor(): Mapper<CountryDto, CountrySummary> {
    override fun map(from: CountryDto): CountrySummary =
        CountrySummary(
            code = from.cca3.orEmpty(),
            flagUrl = from.flags?.png.orEmpty(),
            commonName = from.name?.common.orEmpty(),
            officialName = from.name?.official.orEmpty(),
            capital = formattedCapital(from.capital),
        )
}