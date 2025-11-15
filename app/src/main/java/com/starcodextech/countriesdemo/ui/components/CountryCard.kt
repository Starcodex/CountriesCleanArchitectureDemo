package com.starcodextech.countriesdemo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import com.starcodextech.countriesdemo.ui.preview.PreviewData
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
import com.starcodextech.countriesdemo.ui.theme.countrySummaryFlagImageHeight
import com.starcodextech.countriesdemo.ui.theme.defaultCardElevation
import com.starcodextech.countriesdemo.ui.theme.defaultCornerRadius
import com.starcodextech.countriesdemo.ui.theme.defaultPadding

@Composable
fun CountryCard(
    country: CountrySummaryUiModel,
    onCountryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCountryClick(country.commonName) },
        elevation = CardDefaults.cardElevation(defaultElevation = defaultCardElevation)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = country.flagUrl,
                contentDescription = "Flag of ${country.commonName}",
                modifier = Modifier
                    .size(countrySummaryFlagImageHeight)
                    .clip(RoundedCornerShape(defaultCornerRadius)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(defaultPadding))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = country.commonName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = country.officialName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = country.capital,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Country card – sample"
)
@Composable
fun CountryCardPreview() {
    CountriesDemoTheme {
        CountryCard(
            country = PreviewData.ukSummary,
            onCountryClick = {  }
        )
    }
}