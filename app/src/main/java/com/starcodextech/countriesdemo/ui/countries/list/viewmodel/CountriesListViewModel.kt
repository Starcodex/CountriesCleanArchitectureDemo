package com.starcodextech.countriesdemo.ui.countries.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCase
import com.starcodextech.countriesdemo.ui.common.mapper.toUiError
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import com.starcodextech.countriesdemo.ui.countries.list.state.CountriesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CountriesListViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val uiMapper: Mapper<CountrySummary, CountrySummaryUiModel>
) : ViewModel() {

    private val _uiState = MutableStateFlow(CountriesListUiState())
    val uiState: StateFlow<CountriesListUiState> = _uiState.asStateFlow()

    init {
        loadCountries()
    }

    fun loadCountries() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = getAllCountriesUseCase()) {
                is AppResult.Success -> {
                    val uiList = result.data
                        .map { uiMapper.map(it) }
                        .sortedBy { it.commonName }

                    _uiState.value = CountriesListUiState(
                        isLoading = false,
                        countries = uiList,
                        error = null
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