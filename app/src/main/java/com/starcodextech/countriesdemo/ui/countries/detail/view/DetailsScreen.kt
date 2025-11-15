package com.starcodextech.countriesdemo.ui.countries.detail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.common.state.ScreenUiState
import com.starcodextech.countriesdemo.ui.components.DetailCountryRow
import com.starcodextech.countriesdemo.ui.components.EmptyView
import com.starcodextech.countriesdemo.ui.components.ErrorView
import com.starcodextech.countriesdemo.ui.components.LoadingView
import com.starcodextech.countriesdemo.ui.components.ProvideTopBarState
import com.starcodextech.countriesdemo.ui.countries.detail.model.CountryDetailsUiModel
import com.starcodextech.countriesdemo.ui.countries.detail.viewmodel.DetailsViewModel
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState
import com.starcodextech.countriesdemo.ui.preview.PreviewData
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
import com.starcodextech.countriesdemo.ui.theme.countryDetailsFlagImageHeight
import com.starcodextech.countriesdemo.ui.theme.defaultPadding
import com.starcodextech.countriesdemo.ui.theme.listContentPadding

@Composable
fun DetailsRoute(
    viewModel: DetailsViewModel = hiltViewModel(),
    topBarState: MutableState<TopBarUiState>
) {
    val vmTopBarState by viewModel.topBarState.collectAsStateWithLifecycle()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val loadCountryDetails = viewModel::loadCountryDetails

    ProvideTopBarState(
        topBarState = topBarState,
        override = vmTopBarState
    )

    LaunchedEffect(Unit) {
        viewModel.loadCountryDetails()
    }

    DetailsScreen(
        state = state,
        loadCountryDetails = loadCountryDetails
    )
}

@Composable
fun DetailsScreen(
    state: ScreenUiState<CountryDetailsUiModel>,
    loadCountryDetails: () -> Unit
) {
    when (state) {
        is ScreenUiState.Loading -> {
            LoadingView()
        }

        is ScreenUiState.Error -> {
            ErrorView(error = state.uiError, onRetry = loadCountryDetails)
        }

        is ScreenUiState.Empty -> {
            EmptyView(onRetry = loadCountryDetails)
        }

        is ScreenUiState.Success<CountryDetailsUiModel> -> {
            DetailsView(country = state.content)
        }
    }
}

@Composable
fun DetailsView(
    country: CountryDetailsUiModel
) {
    Column(
        modifier = Modifier
            .padding(defaultPadding)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {

        AsyncImage(
            model = country.flagUrl,
            contentDescription = "Flag of ${country.commonName}",
            modifier = Modifier
                .fillMaxWidth()
                .height(countryDetailsFlagImageHeight)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultPadding),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = country.commonName,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = " (${country.officialName})",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = listContentPadding)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = defaultPadding)
        ) {
            DetailCountryRow(stringResource(R.string.capital, country.capital))
            DetailCountryRow(stringResource(R.string.region, country.region))
            DetailCountryRow(stringResource(R.string.subregion, country.subRegion))
            DetailCountryRow(stringResource(R.string.languages, country.languages))
            DetailCountryRow(stringResource(R.string.currencies, country.currencies))
            DetailCountryRow(stringResource(R.string.population, country.population))
            DetailCountryRow(stringResource(R.string.car_driver_side, country.carDriverSide))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsView_Preview() {
    CountriesDemoTheme {
        DetailsView(country = PreviewData.ukDetails)
    }
}