package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import com.starcodextech.countriesdemo.ui.preview.PreviewData
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
import com.starcodextech.countriesdemo.ui.theme.listContentPadding

@Composable
fun CountriesList(
    countries: List<CountrySummaryUiModel>,
    onCountryClick: (name: String) -> Unit
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(stringResource(R.string.tag_countries_list)),
        contentPadding = PaddingValues(listContentPadding)
    ) {
        items(
            items = countries,
            key = { it.code }
        ) { country ->
            CountryCard(
                country = country,
                onCountryClick = { onCountryClick(country.commonName) }
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Countries list"
)
@Composable
fun CountriesListPreview() {
    CountriesDemoTheme {
        CountriesList(
            countries = PreviewData.sampleCountrySummaryList,
            onCountryClick = {  }
        )
    }
}