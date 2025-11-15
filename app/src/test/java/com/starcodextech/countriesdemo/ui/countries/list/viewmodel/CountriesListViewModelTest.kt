package com.starcodextech.countriesdemo.ui.countries.list.viewmodel

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCase
import com.starcodextech.countriesdemo.ui.common.mapper.toUiError
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import io.mockk.coEvery
import io.mockk.mockk
import com.starcodextech.countriesdemo.common.coroutines.TestDispatchers
import com.starcodextech.countriesdemo.data.DataTest.colombiaCountrySummaryExpected
import com.starcodextech.countriesdemo.data.DataTest.ukCountrySummaryExpected
import com.starcodextech.countriesdemo.ui.common.state.ScreenUiState
import com.starcodextech.countriesdemo.ui.common.state.ScreenUiState.Success
import com.starcodextech.countriesdemo.ui.countries.list.mapper.CountrySummaryUiMapper
import com.starcodextech.countriesdemo.ui.countries.list.state.CountriesListSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountriesListViewModelTest {

    private lateinit var useCase: GetAllCountriesUseCase
    private lateinit var uiMapper: Mapper<CountrySummary, CountrySummaryUiModel>

    private val domainList = listOf(
        ukCountrySummaryExpected,
        colombiaCountrySummaryExpected
    )

    @Before
    fun setUp() {
        useCase = mockk()
        uiMapper = CountrySummaryUiMapper()
    }

    @Test
    fun `given useCase returns Success when loadCountries then state is Success with data`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val dispatchers = TestDispatchers(dispatcher)
        coEvery { useCase.invoke() } returns AppResult.Success(domainList)

        val viewModel = CountriesListViewModel(
            getAllCountriesUseCase = useCase,
            uiMapper = uiMapper,
            dispatchers = dispatchers
        )

        viewModel.loadCountries()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is ScreenUiState<CountriesListSuccess>)

        val content = (state as Success).content
        assertTrue(content is CountriesListSuccess.WithData)
        val countries = (content as CountriesListSuccess.WithData).countries
        assertEquals(2, countries.size)
    }

    @Test
    fun `given useCase returns Error when loadCountries then state is Error`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val dispatchers = TestDispatchers(dispatcher)

        val expectedError = AppError.Network
        coEvery { useCase.invoke() } returns AppResult.Error(expectedError)

        val viewModel = CountriesListViewModel(
            getAllCountriesUseCase = useCase,
            uiMapper = uiMapper,
            dispatchers = dispatchers
        )

        viewModel.loadCountries()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is ScreenUiState.Error)
        val uiError = (state as ScreenUiState.Error).uiError
        assertEquals(expectedError.toUiError().messageRes, uiError.messageRes)
    }

    @Test
    fun `given useCase returns Success with empty list when loadCountries then state is Empty`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val dispatchers = TestDispatchers(dispatcher)
        coEvery { useCase.invoke() } returns AppResult.Success(emptyList())

        val viewModel = CountriesListViewModel(
            getAllCountriesUseCase = useCase,
            uiMapper = uiMapper,
            dispatchers = dispatchers
        )

        viewModel.loadCountries()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is ScreenUiState.Empty)
    }

    @Test
    fun `given loaded countries when search with no matches then state is Success with NoData`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val dispatchers = TestDispatchers(dispatcher)
        coEvery { useCase.invoke() } returns AppResult.Success(domainList)

        val viewModel = CountriesListViewModel(
            getAllCountriesUseCase = useCase,
            uiMapper = uiMapper,
            dispatchers = dispatchers
        )

        viewModel.loadCountries()
        advanceUntilIdle()

        viewModel.observeSearch()
        viewModel.onSearchQueryChanged("ZZZ")
        advanceTimeBy(300)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is ScreenUiState.Success)
        val content = (state as ScreenUiState.Success).content
        assertTrue(content is CountriesListSuccess.NoData)
    }
}