package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
import com.starcodextech.countriesdemo.ui.theme.searchFieldPadding

@Composable
fun SearchField(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
){
    OutlinedTextField(
        value = searchQuery,
        onValueChange = {
            onSearchQueryChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(searchFieldPadding),
        placeholder = { Text(stringResource(R.string.search_field_hint)) }
    )
}

@Preview(
    showBackground = true,
    name = "Search Field"
)
@Composable
fun SearchFieldPreview() {
    CountriesDemoTheme {
        SearchField(
            searchQuery = "Col",
            onSearchQueryChange = {  }
        )
    }
}

@Preview(
    showBackground = true,
    name = "Search Field Empty Showing Hint"
)
@Composable
fun SearchFieldEmptyPreview() {
    CountriesDemoTheme {
        SearchField(
            searchQuery = "",
            onSearchQueryChange = {  }
        )
    }
}