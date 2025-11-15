package com.starcodextech.countriesdemo.ui.countries.detail.mapper

import com.starcodextech.countriesdemo.data.DataTest.ukCountryDetailsExpected
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CountryDetailsUiMapperTest {

    private lateinit var mapper: CountryDetailsUiMapper

    @Before
    fun setUp() {
        mapper = CountryDetailsUiMapper()
    }

    @Test
    fun `given domain CountryDetails when map then returns expected Ui model`() {
        val domain: CountryDetails = ukCountryDetailsExpected

        val uiModel = mapper.map(domain)

        assertEquals(domain.flagUrl, uiModel.flagUrl)
        assertEquals(domain.commonName, uiModel.commonName)
        assertEquals(domain.officialName, uiModel.officialName)
        assertEquals(domain.capital, uiModel.capital)
        assertEquals(domain.region, uiModel.region)
        assertEquals(domain.subRegion, uiModel.subRegion)
        assertEquals(domain.languages, uiModel.languages)
        assertEquals(domain.currencies, uiModel.currencies)
        assertEquals(domain.population, uiModel.population)
        assertEquals(domain.carDriverSide, uiModel.carDriverSide)
    }
}