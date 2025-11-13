package com.starcodextech.countriesdemo.ui.countries.list.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.starcodextech.countriesdemo.ui.components.CountriesList
import com.starcodextech.countriesdemo.ui.components.CountriesTopBar
import com.starcodextech.countriesdemo.ui.components.EmptyView
import com.starcodextech.countriesdemo.ui.components.ErrorView
import com.starcodextech.countriesdemo.ui.components.LoadingView
import com.starcodextech.countriesdemo.ui.components.ProvideTopBarState
import com.starcodextech.countriesdemo.ui.countries.list.state.CountriesListUiState
import com.starcodextech.countriesdemo.ui.countries.list.viewmodel.CountriesListViewModel
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState

@Composable
fun CountriesListRoute(
    viewModel: CountriesListViewModel = hiltViewModel(),
    onCountryClick: (String) -> Unit,
    topBarState: MutableState<TopBarUiState>
) {

    ProvideTopBarState(topBarState = topBarState)

    val state by viewModel.uiState.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    CountriesListScreen(
        state = state,
        onRetry = { viewModel.loadCountries() },
        onCountryClick = onCountryClick,
        searchQuery = searchQuery
    )
}

@Composable
fun CountriesListScreen(
    state: CountriesListUiState,
    onRetry: () -> Unit,
    onCountryClick: (code: String) -> Unit,
    searchQuery: MutableState<String>,
) {
    when {
        state.isLoading -> {
            LoadingView()
        }

        state.error != null -> {
            ErrorView(
                error = state.error,
                onRetry = onRetry
            )
        }

        state.countries.isEmpty() -> {
            EmptyView(onRetry = onRetry)
        }

        else -> {
            CountriesList(
                countries = state.countries,
                onCountryClick = onCountryClick
            )
        }
    }
}