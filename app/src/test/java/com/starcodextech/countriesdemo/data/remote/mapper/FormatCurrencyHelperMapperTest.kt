package com.starcodextech.countriesdemo.data.remote.mapper

import com.starcodextech.countriesdemo.data.DataTest.gbpCurrencyMap
import com.starcodextech.countriesdemo.data.DataTest.multipleCurrenciesDto
import com.starcodextech.countriesdemo.data.remote.dto.CurrencyDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FormatCurrencyHelperMapperTest {

    private lateinit var currencies: Map<String, CurrencyDto>

    @Before
    fun setUp() {
        currencies = emptyMap()
    }

    @Test
    fun `given single currency when formatted then returns code and name`() {
        currencies = gbpCurrencyMap

        val result = formattedCurrency(currencies)

        assertEquals("GBP (British pound)", result)
    }

    @Test
    fun `given multiple currencies when formatted then joins with comma`() {
        currencies = multipleCurrenciesDto

        val result = formattedCurrency(currencies)

        assertEquals("USD (United States dollar), EUR (Euro)", result)
    }

    @Test
    fun `given empty currencies when formatted then returns default value`() {
        currencies = emptyMap()

        val result = formattedCurrency(currencies)

        assertEquals("N/A", result)
    }

    @Test
    fun `given null currencies when formatted then returns default value`() {
        val nullCurrencies = null

        val result = formattedCurrency(nullCurrencies)

        assertEquals("N/A", result)
    }
}