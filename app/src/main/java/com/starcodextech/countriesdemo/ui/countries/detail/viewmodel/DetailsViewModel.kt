package com.starcodextech.countriesdemo.ui.countries.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starcodextech.countriesdemo.common.coroutines.AppDispatchers
import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.detail.usecase.CountryDetailsUseCase
import com.starcodextech.countriesdemo.ui.common.mapper.toUiError
import com.starcodextech.countriesdemo.ui.countries.detail.model.CountryDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.starcodextech.countriesdemo.ui.countries.detail.navigation.CountryDetailsNavigation.COUNTRY_NAME
import com.starcodextech.countriesdemo.ui.countries.detail.state.CountryDetailsUiState
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedState: SavedStateHandle,
    private val countryDetailsUseCase: CountryDetailsUseCase,
    private val uiMapper: Mapper<CountryDetails, CountryDetailsUiModel>,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val countryName: String =
        checkNotNull(savedState[COUNTRY_NAME]) {
            "COUNTRY_NAME arg is required"
        }

    private val _topBarState = MutableStateFlow(
        TopBarUiState(
            titleText = countryName,
            showBack = true
        )
    )
    val topBarState: StateFlow<TopBarUiState> = _topBarState

    private val _uiState = MutableStateFlow(CountryDetailsUiState())
    val uiState: StateFlow<CountryDetailsUiState> = _uiState

    fun loadCountryDetails() {
        viewModelScope.launch(dispatchers.io) {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = countryDetailsUseCase(countryName)) {
                is AppResult.Success -> {
                    val uiModel = uiMapper.map(result.data)

                    _uiState.value = CountryDetailsUiState(
                        isLoading = false,
                        country = uiModel,
                        error = null
                    )

                    _topBarState.value = TopBarUiState(
                        titleText = uiModel.commonName,
                        showBack = true
                    )
                }

                is AppResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.error.toUiError()
                        )
                    }
                }
            }
        }
    }
}