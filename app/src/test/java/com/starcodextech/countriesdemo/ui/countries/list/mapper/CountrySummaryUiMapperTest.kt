package com.starcodextech.countriesdemo.ui.countries.list.mapper

import com.starcodextech.countriesdemo.data.DataTest.ukCountrySummaryExpected
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CountrySummaryUiMapperTest {

    private lateinit var mapper: CountrySummaryUiMapper

    @Before
    fun setUp() {
        mapper = CountrySummaryUiMapper()
    }

    @Test
    fun `given domain CountrySummary when map then returns expected Ui model`() {
        val domain: CountrySummary = ukCountrySummaryExpected

        val uiModel = mapper.map(domain)

        assertEquals(domain.code, uiModel.code)
        assertEquals(domain.flagUrl, uiModel.flagUrl)
        assertEquals(domain.commonName, uiModel.commonName)
        assertEquals(domain.officialName, uiModel.officialName)
        assertEquals(domain.capital, uiModel.capital)
    }
}