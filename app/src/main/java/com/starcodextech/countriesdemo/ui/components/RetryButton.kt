package com.starcodextech.countriesdemo.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme

@Composable
fun RetryButton(
    onRetry: () -> Unit
) {
    Button(onClick = onRetry) {
        Text(stringResource(R.string.retry_button_text))
    }
}

@Preview(
    showBackground = true,
    name = "Loading view"
)
@Composable
fun RetryPreview() {
    CountriesDemoTheme {
        RetryButton {  }
    }
}