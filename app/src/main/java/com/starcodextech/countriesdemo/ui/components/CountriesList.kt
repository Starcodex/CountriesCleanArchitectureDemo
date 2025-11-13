package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import com.starcodextech.countriesdemo.ui.theme.listContentPadding

@Composable
fun CountriesList(
    countries: List<CountrySummaryUiModel>,
    onCountryClick: (name: String) -> Unit
){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
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