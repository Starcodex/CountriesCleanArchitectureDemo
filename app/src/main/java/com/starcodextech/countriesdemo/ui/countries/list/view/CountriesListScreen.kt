package com.starcodextech.countriesdemo.ui.countries.list.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.starcodextech.countriesdemo.ui.components.CountriesList
import com.starcodextech.countriesdemo.ui.components.EmptyView
import com.starcodextech.countriesdemo.ui.components.ErrorView
import com.starcodextech.countriesdemo.ui.components.LoadingView
import com.starcodextech.countriesdemo.ui.components.ProvideTopBarState
import com.starcodextech.countriesdemo.ui.components.SearchField
import com.starcodextech.countriesdemo.ui.countries.list.state.CountriesListUiState
import com.starcodextech.countriesdemo.ui.countries.list.viewmodel.CountriesListViewModel
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState
import com.starcodextech.countriesdemo.ui.theme.defaultPadding

@Composable
fun CountriesListRoute(
    viewModel: CountriesListViewModel = hiltViewModel(),
    onCountryClick: (String) -> Unit,
    topBarState: MutableState<TopBarUiState>
) {

    ProvideTopBarState(topBarState = topBarState)

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()
    val onSearchQueryChanged = viewModel::onSearchQueryChanged

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
        viewModel.observeSearch()
    }

    CountriesListScreen(
        state = state,
        onRetry = { viewModel.refreshCountries() },
        onCountryClick = onCountryClick,
        searchQuery = searchQuery,
        onSearchQueryChanged = onSearchQueryChanged
    )
}

@Composable
fun CountriesListScreen(
    state: CountriesListUiState,
    onRetry: () -> Unit,
    onCountryClick: (code: String) -> Unit,
    searchQuery: State<String>,
    onSearchQueryChanged: (String) -> Unit = { }
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

            Column {

                SearchField(
                    searchQuery = searchQuery.value,
                    onSearchQueryChange = onSearchQueryChanged
                )

                Spacer(modifier = Modifier.height(defaultPadding))

                CountriesList(
                    countries = state.countries,
                    onCountryClick = onCountryClick
                )

            }
        }
    }
}