package com.starcodextech.countriesdemo.data.repository.country.details

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.logger.FakeLogger
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.DataTest.colombiaCountryCode
import com.starcodextech.countriesdemo.data.DataTest.genericCountryDto
import com.starcodextech.countriesdemo.data.remote.api.RestCountriesApi
import com.starcodextech.countriesdemo.data.remote.mapper.CountryDetailsMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CountryDetailsRepositoryImplTest {

    private lateinit var api: RestCountriesApi
    private lateinit var mapper: CountryDetailsMapper
    private lateinit var logger: FakeLogger
    private lateinit var repository: CountryDetailsRepositoryImpl

    @Before
    fun setUp() {
        api = mockk()
        mapper = CountryDetailsMapper()
        logger = FakeLogger()
        repository = CountryDetailsRepositoryImpl(api, mapper, logger)
    }

    @Test
    fun `given api returns country when getCountryByName called, then returns Success`() = runBlocking {
        val dto = genericCountryDto(colombiaCountryCode)
        coEvery { api.getCountryByName(any(), any(), any()) } returns listOf(dto)

        val result = repository.getCountryByName("Colombia")

        assertTrue(result is AppResult.Success)
        result as AppResult.Success
        assertEquals("Country COL", result.data.commonName)
    }

    @Test
    fun `given api returns empty list when getCountryByName called, then returns Unknown error`() = runBlocking {
        coEvery { api.getCountryByName(any(), any(), any()) } returns emptyList()

        val result = repository.getCountryByName("Nowhere")

        assertTrue(result is AppResult.Error)
        result as AppResult.Error
        assertTrue(result.error is AppError.Unknown)
    }
}