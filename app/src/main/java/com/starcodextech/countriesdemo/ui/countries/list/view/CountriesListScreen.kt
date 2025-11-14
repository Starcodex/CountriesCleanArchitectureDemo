package com.starcodextech.countriesdemo.ui.countries.list.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.common.state.ScreenUiState
import com.starcodextech.countriesdemo.ui.components.CountriesList
import com.starcodextech.countriesdemo.ui.components.EmptyView
import com.starcodextech.countriesdemo.ui.components.ErrorView
import com.starcodextech.countriesdemo.ui.components.LoadingView
import com.starcodextech.countriesdemo.ui.components.ProvideTopBarState
import com.starcodextech.countriesdemo.ui.components.SearchField
import com.starcodextech.countriesdemo.ui.countries.list.state.CountriesListSuccess
import com.starcodextech.countriesdemo.ui.countries.list.viewmodel.CountriesListViewModel
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState
import com.starcodextech.countriesdemo.ui.preview.PreviewData
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
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
    state: ScreenUiState<CountriesListSuccess>,
    onRetry: () -> Unit,
    onCountryClick: (code: String) -> Unit,
    searchQuery: State<String>,
    onSearchQueryChanged: (String) -> Unit = { }
) {
    when (state) {

        is ScreenUiState.Loading -> {
            LoadingView()
        }

        is ScreenUiState.Error -> {
            ErrorView(
                error = state.uiError,
                onRetry = onRetry
            )
        }

        is ScreenUiState.Empty -> {
            EmptyView(onRetry = onRetry)
        }

        is ScreenUiState.Success -> {
            Column {

                SearchField(
                    searchQuery = searchQuery.value,
                    onSearchQueryChange = onSearchQueryChanged
                )

                Spacer(modifier = Modifier.height(defaultPadding))

                when (val content = state.content) {
                    is CountriesListSuccess.WithData -> {
                        CountriesList(
                            countries = content.countries,
                            onCountryClick = onCountryClick
                        )
                    }

                    is CountriesListSuccess.NoData -> {
                        Text(
                            text = stringResource(R.string.search_query_empty_results, searchQuery.value),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(defaultPadding),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Countries list – Success")
@Composable
fun CountriesListScreen_Success_Preview() {
    CountriesDemoTheme {
        CountriesListScreen(
            state = ScreenUiState.Success(
                CountriesListSuccess.WithData(
                    countries = PreviewData.sampleCountrySummaryList
                )
            ),
            searchQuery = remember { mutableStateOf("") },
            onRetry = { },
            onCountryClick = {  },
            onSearchQueryChanged = { }
        )
    }
}