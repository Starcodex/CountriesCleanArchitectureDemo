package com.starcodextech.countriesdemo.data.remote.mapper

import org.junit.Assert.assertEquals
import com.starcodextech.countriesdemo.data.DataTest.bogotaCapitalMap
import com.starcodextech.countriesdemo.data.DataTest.bogotaCapitalName
import com.starcodextech.countriesdemo.data.DataTest.londonCapitalName
import com.starcodextech.countriesdemo.data.DataTest.multipleCapitalMap
import org.junit.Before
import org.junit.Test

class FormatCapitalHelperMapperTest {

    private lateinit var capital: List<String>

    @Before
    fun setUp() {
        capital = emptyList()
    }

    @Test
    fun `given single capital when formatted then returns that capital`() {
        capital = bogotaCapitalMap

        val result = formattedCapital(capital)

        assertEquals(bogotaCapitalName, result)
    }

    @Test
    fun `given multiple capitals when formatted then joins with comma`() {
        capital = multipleCapitalMap

        val result = formattedCapital(capital)

        assertEquals("$londonCapitalName, $bogotaCapitalName", result)
    }

    @Test
    fun `given empty capital list when formatted then returns NA`() {
        capital = emptyList()

        val result = formattedCapital(capital)

        assertEquals("N/A", result)
    }

    @Test
    fun `given null capital when formatted then returns NA`() {

        val nullCapital = null

        val result = formattedCapital(nullCapital)

        assertEquals("N/A", result)
    }
}