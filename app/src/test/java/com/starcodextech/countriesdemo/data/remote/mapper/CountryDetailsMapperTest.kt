package com.starcodextech.countriesdemo.data.remote.mapper

import com.starcodextech.countriesdemo.data.DataTest.nullCountryDto
import com.starcodextech.countriesdemo.data.DataTest.ukCountryDetailsExpected
import com.starcodextech.countriesdemo.data.DataTest.ukCountryDto
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CountryDetailsMapperTest {

    private lateinit var mapper: CountryDetailsMapper

    @Before
    fun setUp() {
        mapper = CountryDetailsMapper()
    }

    @Test
    fun `given valid CountryDto when map then returns expected CountryDetails`() {
        val dto = ukCountryDto

        val result: CountryDetails = mapper.map(dto)

        val expected = ukCountryDetailsExpected

        assertEquals(expected, result)
    }

    @Test
    fun `given CountryDto with nullable fields when map then applies defaults`() {
        val dto = nullCountryDto

        val result = mapper.map(dto)

        assertEquals("N/A", result.capital)
        assertEquals("", result.subRegion)
        assertEquals("", result.languages)
        assertEquals("N/A", result.currencies)
    }
}