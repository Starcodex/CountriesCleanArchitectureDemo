package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.starcodextech.countriesdemo.common.error.UiError
import com.starcodextech.countriesdemo.ui.theme.defaultViewMessagePadding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import com.starcodextech.countriesdemo.ui.theme.defaultPadding

@Composable
fun ErrorView(
    error: UiError,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(defaultViewMessagePadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(error.messageRes),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(defaultPadding))
            RetryButton(onRetry = onRetry)
        }
    }
}