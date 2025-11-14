package com.starcodextech.countriesdemo.ui.preview

import com.starcodextech.countriesdemo.ui.countries.detail.model.CountryDetailsUiModel
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel

object PreviewData {

    val ukSummary = CountrySummaryUiModel(
        code = "1",
        commonName = "United Kingdom",
        officialName = "United Kingdom of Great Britain and Northern Ireland",
        capital = "London",
        flagUrl = "https://flagcdn.com/gb.svg"
    )

    private val colombiaSummary = CountrySummaryUiModel(
        code = "2",
        commonName = "Colombia",
        officialName = "Republic of Colombia",
        capital = "Bogotá",
        flagUrl = "https://flagcdn.com/co.svg"
    )

    private val japanSummary = CountrySummaryUiModel(
        code = "3",
        commonName = "Japan",
        officialName = "Japan",
        capital = "Tokyo",
        flagUrl = "https://flagcdn.com/jp.svg"
    )

    val sampleCountrySummaryList = listOf(
        ukSummary,
        colombiaSummary,
        japanSummary
    )


    // --- Details
    val ukDetails = CountryDetailsUiModel(
        commonName = "United Kingdom",
        officialName = "United Kingdom of Great Britain and Northern Ireland",
        capital = "London",
        region = "Europe",
        subRegion = "Northern Europe",
        languages = "English",
        currencies = "GBP (British pound, £)",
        population = "67,215,293",
        carDriverSide = "Left",
        flagUrl = "https://flagcdn.com/gb.svg"
    )

    val colombiaDetails = CountryDetailsUiModel(
        commonName = "Colombia",
        officialName = "Republic of Colombia",
        capital = "Bogotá",
        region = "Americas",
        subRegion = "South America",
        languages = "Spanish",
        currencies = "COP (Colombian peso, $)",
        population = "50,882,884",
        carDriverSide = "Right",
        flagUrl = "https://flagcdn.com/co.svg"
    )

    val japanDetails = CountryDetailsUiModel(
        commonName = "Japan",
        officialName = "Japan",
        capital = "Tokyo",
        region = "Asia",
        subRegion = "Eastern Asia",
        languages = "Japanese",
        currencies = "JPY (Japanese yen, ¥)",
        population = "125,836,021",
        carDriverSide = "Left",
        flagUrl = "https://flagcdn.com/jp.svg"
    )
}