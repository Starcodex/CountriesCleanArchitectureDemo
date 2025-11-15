package com.starcodextech.countriesdemo.ui.countries.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.starcodextech.countriesdemo.common.coroutines.AppDispatchers
import com.starcodextech.countriesdemo.common.coroutines.TestDispatchers
import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.DataTest.ukCountryDetailsExpected
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.detail.usecase.CountryDetailsUseCase
import com.starcodextech.countriesdemo.ui.common.mapper.toUiError
import com.starcodextech.countriesdemo.ui.common.state.ScreenUiState
import com.starcodextech.countriesdemo.ui.countries.detail.mapper.CountryDetailsUiMapper
import com.starcodextech.countriesdemo.ui.countries.detail.model.CountryDetailsUiModel
import com.starcodextech.countriesdemo.ui.countries.detail.navigation.CountryDetailsNavigation.COUNTRY_NAME
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    private lateinit var useCase: CountryDetailsUseCase
    private lateinit var uiMapper: Mapper<CountryDetails, CountryDetailsUiModel>

    @Before
    fun setUp() {
        useCase = mockk()
        uiMapper = CountryDetailsUiMapper()
    }

    @Test
    fun `given useCase returns Success when loadCountryDetails then state has country and topBar updated`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val dispatchers: AppDispatchers = TestDispatchers(dispatcher)
            val domainDetails = ukCountryDetailsExpected

            coEvery { useCase.invoke(any()) } returns AppResult.Success(domainDetails)

            val savedStateHandle = SavedStateHandle(
                mapOf(COUNTRY_NAME to "United Kingdom")
            )

            val viewModel = DetailsViewModel(
                savedState = savedStateHandle,
                countryDetailsUseCase = useCase,
                uiMapper = uiMapper,
                dispatchers = dispatchers
            )

            viewModel.loadCountryDetails()
            advanceUntilIdle()

            val uiState: ScreenUiState<CountryDetailsUiModel> = viewModel.uiState.value
            assertFalse(uiState is ScreenUiState.Loading)
            assertFalse(uiState is ScreenUiState.Error)
            assertTrue(uiState is ScreenUiState.Success)

            val content = (uiState as ScreenUiState.Success).content
            assertEquals(domainDetails.commonName, content.commonName)

            val topBarState: TopBarUiState = viewModel.topBarState.value
            assertTrue(topBarState.showBack)
            assertEquals(domainDetails.commonName, topBarState.titleText)
        }

    @Test
    fun `given useCase returns Error when loadCountryDetails then state has error and no country`() =
        runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val dispatchers: AppDispatchers = TestDispatchers(dispatcher)
            val expectedError = AppError.NotFound

            coEvery { useCase.invoke(any()) } returns AppResult.Error(expectedError)

            val savedStateHandle = SavedStateHandle(
                mapOf(COUNTRY_NAME to "Nowhere")
            )

            val viewModel = DetailsViewModel(
                savedState = savedStateHandle,
                countryDetailsUseCase = useCase,
                uiMapper = uiMapper,
                dispatchers = dispatchers
            )

            viewModel.loadCountryDetails()
            advanceUntilIdle()

            val uiState: ScreenUiState<CountryDetailsUiModel> = viewModel.uiState.value
            assertFalse(uiState is ScreenUiState.Loading)
            assertFalse(uiState is ScreenUiState.Success)
            assertTrue(uiState is ScreenUiState.Error)

            val error = (uiState as ScreenUiState.Error).uiError

            assertEquals(expectedError.toUiError().messageRes, error.messageRes)
        }
}