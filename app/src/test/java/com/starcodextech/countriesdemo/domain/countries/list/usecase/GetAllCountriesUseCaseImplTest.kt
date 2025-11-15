package com.starcodextech.countriesdemo.domain.countries.list.usecase

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.DataTest.colombiaCountrySummaryExpected
import com.starcodextech.countriesdemo.data.DataTest.ukCountrySummaryExpected
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.repository.CountriesListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAllCountriesUseCaseImplTest {

    private lateinit var repository: CountriesListRepository
    private lateinit var useCase: GetAllCountriesUseCaseImpl

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetAllCountriesUseCaseImpl(repository)
    }

    @Test
    fun `given repository returns Success when invoke then propagates Success`() = runBlocking {
        val expectedList: List<CountrySummary> = listOf(
            ukCountrySummaryExpected,
            colombiaCountrySummaryExpected
        )
        coEvery { repository.getAllCountries() } returns AppResult.Success(expectedList)

        val result = useCase()

        assertTrue(result is AppResult.Success)
        result as AppResult.Success
        assertEquals(expectedList, result.data)
    }

    @Test
    fun `given repository returns Error when invoke then propagates Error`() = runBlocking {
        val expectedError = AppError.Network
        coEvery { repository.getAllCountries() } returns AppResult.Error(expectedError)

        val result = useCase()

        assertTrue(result is AppResult.Error)
        result as AppResult.Error
        assertEquals(expectedError, result.error)
    }
}