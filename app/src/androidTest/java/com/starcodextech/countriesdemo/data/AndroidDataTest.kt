package com.starcodextech.countriesdemo.data

import com.starcodextech.countriesdemo.data.remote.dto.CarDto
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.data.remote.dto.CurrencyDto
import com.starcodextech.countriesdemo.data.remote.dto.FlagsDto
import com.starcodextech.countriesdemo.data.remote.dto.NameDto
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary

object AndroidDataTest {

    // ==========
    // DTOs (remote)
    // ==========

    // Country code constants
    val ukCountryCode = "GBR"
    val colombiaCountryCode = "COL"


    // Currencies DTOs
    val usdCurrencyCode = "USD"
    val usdCurrencyDto = CurrencyDto(name = "United States dollar", symbol = "$")

    val euroCurrencyCode = "EUR"
    val euroCurrencyDto = CurrencyDto(name = "Euro", symbol = "€")

    val multipleCurrenciesDto = mapOf(
        usdCurrencyCode to usdCurrencyDto,
        euroCurrencyCode to euroCurrencyDto
    )

    val gbpCurrencyCode = "GBP"
    val gbpCurrencyDto = CurrencyDto(name = "British pound", symbol = "£")

    val gbpCurrencyMap = mapOf(
        gbpCurrencyCode to gbpCurrencyDto
    )

    // Capital cities DTOs
    val londonCapitalName = "London"
    val bogotaCapitalName = "Bogotá"
    val londonCapitalMap = listOf(londonCapitalName)
    val bogotaCapitalMap = listOf(bogotaCapitalName)

    val multipleCapitalMap = listOf(
        londonCapitalName,
        bogotaCapitalName
    )

    // Country DTOs
    val ukCountryDto = CountryDto(
        name = NameDto(
            common = "United Kingdom",
            official = "United Kingdom of Great Britain and Northern Ireland"
        ),
        capital = londonCapitalMap,
        cca3 = ukCountryCode,
        region = "Europe",
        subregion = "Northern Europe",
        languages = mapOf("eng" to "English"),
        currencies = gbpCurrencyMap,
        population = 67_215_293,
        car = CarDto(
            signs = listOf("GB"),
            side = "left"
        ),
        flags = FlagsDto(
            png = "https://flagcdn.com/w320/gb.png",
            svg = "https://flagcdn.com/gb.svg",
            alt = "Flag of the United Kingdom of Great Britain and Northern Ireland"
        )
    )

    val colombiaCountryDto = CountryDto(
        name = NameDto(
            common = "Colombia",
            official = "Republic of Colombia"
        ),
        capital = bogotaCapitalMap,
        cca3 = colombiaCountryCode,
        region = "Americas",
        subregion = "South America",
        languages = mapOf("spa" to "Spanish"),
        currencies = mapOf(
            "COP" to CurrencyDto(
                name = "Colombian peso",
                symbol = "$"
            )
        ),
        population = 50_882_884,
        car = CarDto(
            signs = listOf("CO"),
            side = "right"
        ),
        flags = FlagsDto(
            png = "https://flagcdn.com/w320/co.png",
            svg = "https://flagcdn.com/co.svg",
            alt = "Flag of Colombia"
        )
    )

    val nullCountryDto = CountryDto(
        name = NameDto(
            common = "Nullandia",
            official = "Republic of Nullandia"
        ),
        capital = emptyList(),
        cca3 = "NUL",
        region = "Testing",
        subregion = null,
        languages = null,
        currencies = null,
        population = null,
        car = null,
        flags = null
    )


    fun genericCountryDto(code: String): CountryDto =
        CountryDto(
            name = NameDto(
                common = "Country $code",
                official = "Official $code"
            ),
            capital = listOf("Capital $code"),
            cca3 = code,
            region = "Region",
            subregion = "Subregion",
            languages = emptyMap(),
            currencies = emptyMap(),
            population = 1,
            car = CarDto(signs = emptyList(), side = "right"),
            flags = FlagsDto(png = "png-$code", svg = "svg-$code")
        )

    // ==========
    // Expected domain models
    // ==========

    // Expected CountryDetails after mapping
    val ukCountryDetailsExpected = CountryDetails(
        flagUrl = "https://flagcdn.com/w320/gb.png",
        commonName = "United Kingdom",
        officialName = "United Kingdom of Great Britain and Northern Ireland",
        capital = "London",
        region = "Europe",
        subRegion = "Northern Europe",
        languages = "English",
        currencies = "GBP (British pound)",
        population = "67215293",
        carDriverSide = "left"
    )

    // expected CountrySummary after mapping

    val ukCountrySummaryExpected = CountrySummary(
        code = "GBR",
        flagUrl = "https://flagcdn.com/w320/gb.png",
        commonName = "United Kingdom",
        officialName = "United Kingdom of Great Britain and Northern Ireland",
        capital = "London"
    )

    val colombiaCountryDetailsExpected = CountryDetails(
        flagUrl = "https://flagcdn.com/w320/co.png",
        commonName = "Colombia",
        officialName = "Republic of Colombia",
        capital = "Bogotá",
        region = "Americas",
        subRegion = "South America",
        languages = "Spanish",
        currencies = "COP (Colombian peso)",
        population = "50882884",
        carDriverSide = "right"
    )

    val colombiaCountrySummaryExpected = CountrySummary(
        code = "COL",
        flagUrl = "https://flagcdn.com/w320/co.png",
        commonName = "Colombia",
        officialName = "Republic of Colombia",
        capital = "Bogotá"
    )


}