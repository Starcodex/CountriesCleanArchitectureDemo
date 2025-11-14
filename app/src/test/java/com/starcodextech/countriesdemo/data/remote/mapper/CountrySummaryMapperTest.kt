package com.starcodextech.countriesdemo.data.remote.mapper

import com.starcodextech.countriesdemo.data.DataTest.colombiaCountryDto
import com.starcodextech.countriesdemo.data.DataTest.colombiaCountrySummaryExpected
import com.starcodextech.countriesdemo.data.DataTest.nullCountryDto
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CountrySummaryMapperTest {

    private lateinit var mapper: CountrySummaryMapper

    @Before
    fun setUp() {
        mapper = CountrySummaryMapper()
    }

    @Test
    fun `given valid CountryDto when map then returns expected summary`() {
        val dto = colombiaCountryDto

        val result: CountrySummary = mapper.map(dto)

        val expected = colombiaCountrySummaryExpected

        assertEquals(result, expected)
    }

    @Test
    fun `given CountryDto without capital when map then uses NA`() {
        val dto = nullCountryDto

        val summary = mapper.map(dto)

        assertEquals("N/A", summary.capital)
    }
}