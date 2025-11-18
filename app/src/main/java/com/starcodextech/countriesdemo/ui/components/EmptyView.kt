package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
import com.starcodextech.countriesdemo.ui.theme.defaultViewMessagePadding

@Composable
fun EmptyView(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(defaultViewMessagePadding)
            .testTag(stringResource(R.string.tag_countries_empty_view)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.ui_empty_results),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(defaultViewMessagePadding))
            RetryButton(onRetry = onRetry)
        }
    }
}

@Preview(
    showBackground = true,
    name = "Empty view – default"
)
@Composable
fun EmptyViewPreview() {
    CountriesDemoTheme {
        EmptyView(
            onRetry = {  }
        )
    }
}