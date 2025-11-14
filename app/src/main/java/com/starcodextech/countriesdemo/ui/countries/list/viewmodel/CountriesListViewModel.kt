package com.starcodextech.countriesdemo.ui.countries.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starcodextech.countriesdemo.common.coroutines.AppDispatchers
import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCase
import com.starcodextech.countriesdemo.ui.common.mapper.toUiError
import com.starcodextech.countriesdemo.ui.common.state.ScreenUiState
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import com.starcodextech.countriesdemo.ui.countries.list.state.CountriesListSuccess
import com.starcodextech.countriesdemo.ui.countries.list.state.CountriesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@HiltViewModel
class CountriesListViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val uiMapper: Mapper<CountrySummary, CountrySummaryUiModel>,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ScreenUiState<CountriesListSuccess>>(ScreenUiState.Loading)
    val uiState: StateFlow<ScreenUiState<CountriesListSuccess>> = _uiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var allCountries: List<CountrySummaryUiModel> = emptyList()

    private var hasLoaded = false

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun observeSearch() {
        viewModelScope.launch(dispatchers.default) {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (allCountries.isEmpty()) return@collectLatest

                    val filtered = if (query.isBlank()) {
                        allCountries
                    } else {
                        allCountries.filter {
                            it.commonName.contains(query, ignoreCase = true)
                        }
                    }

                    val content =
                        if (filtered.isEmpty() && query.isNotBlank()) {
                            CountriesListSuccess.NoData
                        } else {
                            CountriesListSuccess.WithData(filtered)
                        }

                    _uiState.value = ScreenUiState.Success(content)
                }
        }
    }

    fun loadCountries(forceRefresh: Boolean = false) {
        if (!forceRefresh && hasLoaded) return

        viewModelScope.launch(dispatchers.io) {
            _uiState.value = ScreenUiState.Loading

            when (val result = getAllCountriesUseCase()) {
                is AppResult.Success -> {
                    val uiList = result.data
                        .map { uiMapper.map(it) }
                        .sortedBy { it.commonName }

                    allCountries = uiList
                    hasLoaded = true

                    _uiState.value = if (uiList.isEmpty()) {
                        ScreenUiState.Empty
                    } else {
                        ScreenUiState.Success(
                            CountriesListSuccess.WithData(uiList)
                        )
                    }
                }

                is AppResult.Error -> {
                    _uiState.value = ScreenUiState.Error(result.error.toUiError())
                }
            }
        }
    }

    fun refreshCountries() {
        loadCountries(forceRefresh = true)
    }
}