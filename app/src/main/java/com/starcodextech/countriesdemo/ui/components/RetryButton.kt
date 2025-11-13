package com.starcodextech.countriesdemo.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.starcodextech.countriesdemo.R

@Composable
fun RetryButton(
    onRetry: () -> Unit
) {
    Button(onClick = onRetry) {
        Text(stringResource(R.string.retry_button_text))
    }
}