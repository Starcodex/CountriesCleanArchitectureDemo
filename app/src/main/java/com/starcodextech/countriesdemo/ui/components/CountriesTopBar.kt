package com.starcodextech.countriesdemo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesTopBar(
    topBarState: TopBarUiState,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            when {
                topBarState.titleText != null -> Text(topBarState.titleText, modifier = Modifier.testTag(
                    stringResource(R.string.tag_countries_top_bar_title)
                ))

                topBarState.titleRes != null -> Text(stringResource(topBarState.titleRes), modifier = Modifier.testTag(
                        stringResource(R.string.tag_countries_top_bar_title)
                        ))

                else -> Text(stringResource(R.string.app_name), modifier = Modifier.testTag(
                    stringResource(R.string.tag_countries_top_bar_title)
                ))
            }
        },
        navigationIcon = {
            if (topBarState.showBack) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag(stringResource(R.string.tag_countries_back_button))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun ProvideTopBarState(
    topBarState: MutableState<TopBarUiState>,
    override: TopBarUiState? = null,
    default: TopBarUiState = TopBarUiState(
        titleRes = R.string.ctop_bar_title,
        showBack = false
    )
) {
    SideEffect {
        topBarState.value = override ?: default
    }
}

@Preview(
    showBackground = true,
    name = "TopBar – List (without back)"
)
@Composable
fun CountriesTopBarListPreview() {
    CountriesDemoTheme {
        CountriesTopBar(
            topBarState = TopBarUiState(
                titleRes = R.string.ctop_bar_title,
                showBack = false
            ),
            onBackClick = {  }
        )
    }
}

@Preview(
    showBackground = true,
    name = "TopBar – Detail (with back)"
)
@Composable
fun CountriesTopBarDetailPreview() {
    CountriesDemoTheme {
        CountriesTopBar(
            topBarState = TopBarUiState(
                titleText = "United Kingdom",
                showBack = true
            ),
            onBackClick = {  }
        )
    }
}