package com.starcodextech.countriesdemo.domain.countries.detail.usecase

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.DataTest.ukCountryDetailsExpected
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.detail.repository.CountryDetailsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CountryDetailsUseCaseImplTest {

    private lateinit var repository: CountryDetailsRepository
    private lateinit var useCase: CountryDetailsUseCaseImpl

    @Before
    fun setUp() {
        repository = mockk()
        useCase = CountryDetailsUseCaseImpl(repository)
    }

    @Test
    fun `given repository returns Success when invoke then propagates Success`() = runBlocking {
        val expectedDetails: CountryDetails = ukCountryDetailsExpected
        coEvery { repository.getCountryByName(any()) } returns AppResult.Success(expectedDetails)

        val result = useCase("United Kingdom")

        assertTrue(result is AppResult.Success)
        result as AppResult.Success
        assertEquals(expectedDetails, result.data)
    }

    @Test
    fun `given repository returns Error when invoke then propagates Error`() = runBlocking {
        val expectedError = AppError.NotFound
        coEvery { repository.getCountryByName(any()) } returns AppResult.Error(expectedError)

        val result = useCase("UnknownCountry")

        assertTrue(result is AppResult.Error)
        result as AppResult.Error
        assertEquals(expectedError, result.error)
    }
}