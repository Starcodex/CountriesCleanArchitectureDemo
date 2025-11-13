package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.theme.searchFieldPadding

@Composable
fun SearchField(
    searchQuery: MutableState<String>,
    onSearchQueryChange: (String) -> Unit
){
    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = {
            searchQuery.value = it
            onSearchQueryChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(searchFieldPadding),
        placeholder = { Text(stringResource(R.string.search_field_hint)) }
    )
}