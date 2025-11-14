package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.starcodextech.countriesdemo.ui.theme.listContentPadding

@Composable
fun DetailCountryRow(value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = listContentPadding),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }
}