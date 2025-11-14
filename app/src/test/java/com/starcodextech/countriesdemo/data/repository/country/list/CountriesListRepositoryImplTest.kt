package com.starcodextech.countriesdemo.data.repository.country.list

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.logger.FakeLogger
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.DataTest.colombiaCountryCode
import com.starcodextech.countriesdemo.data.remote.api.RestCountriesApi
import com.starcodextech.countriesdemo.data.DataTest.genericCountryDto
import com.starcodextech.countriesdemo.data.DataTest.ukCountryCode
import com.starcodextech.countriesdemo.data.remote.mapper.CountrySummaryMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CountriesListRepositoryImplTest {

    private lateinit var api: RestCountriesApi
    private lateinit var mapper: CountrySummaryMapper
    private lateinit var repository: CountriesListRepositoryImpl
    private lateinit var logger: FakeLogger

    @Before
    fun setUp() {
        api = mockk()
        mapper = CountrySummaryMapper()
        logger = FakeLogger()
        repository = CountriesListRepositoryImpl(api, mapper, logger)
    }

    @Test
    fun `given api returns data when getAllCountries then returns Success`() = runBlocking {
        val dtoList = listOf(
            genericCountryDto(ukCountryCode),
            genericCountryDto(colombiaCountryCode)
        )
        coEvery { api.getAllCountries(any()) } returns dtoList

        val result = repository.getAllCountries()

        assertTrue(result is AppResult.Success)
        result as AppResult.Success
        assertEquals(2, result.data.size)
        assertEquals(ukCountryCode, result.data.first().code)
    }

    @Test
    fun `given api throws IOException when getAllCountries then returns Network error`() = runBlocking {
        coEvery { api.getAllCountries(any()) } throws IOException()

        val result = repository.getAllCountries()

        assertTrue(result is AppResult.Error)
        result as AppResult.Error
        assertEquals(AppError.Network, result.error)
    }

}