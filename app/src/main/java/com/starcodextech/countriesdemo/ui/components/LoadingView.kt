package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.starcodextech.countriesdemo.ui.theme.defaultPadding
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(defaultPadding),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview(
    showBackground = true,
    name = "Loading view"
)
@Composable
fun LoadingViewPreview() {
    CountriesDemoTheme {
        LoadingView()
    }
}