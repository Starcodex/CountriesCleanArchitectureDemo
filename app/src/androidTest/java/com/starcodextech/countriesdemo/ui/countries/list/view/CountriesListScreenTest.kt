package com.starcodextech.countriesdemo.ui.countries.list.view

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.common.error.UiError
import com.starcodextech.countriesdemo.ui.common.state.ScreenUiState
import com.starcodextech.countriesdemo.ui.countries.list.state.CountriesListSuccess
import com.starcodextech.countriesdemo.ui.preview.PreviewData
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
import org.junit.Rule
import org.junit.Test

class CountriesListScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun givenLoadingStateWhenScreenShownThenShowsLoadingView() {


        composeRule.setContent {
            CountriesDemoTheme {
                CountriesListScreen(
                    state = ScreenUiState.Loading,
                    onRetry = {},
                    onCountryClick = {},
                    searchQuery = remember { mutableStateOf("") },
                    onSearchQueryChanged = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_loading_view))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_list))
            .assertDoesNotExist()
    }

    @Test
    fun givenErrorStateWhenScreenShownThenShowsErrorMessageAndRetryButton() {
        val errorText = composeRule.activity.getString(R.string.ui_generic_error)

        composeRule.setContent {
            CountriesDemoTheme {
                CountriesListScreen(
                    state = ScreenUiState.Error(
                        uiError = UiError.Generic()
                    ),
                    onRetry = {},
                    onCountryClick = {},
                    searchQuery = remember { mutableStateOf("") },
                    onSearchQueryChanged = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_error_view))
            .assertIsDisplayed()

        composeRule.onNodeWithText(errorText)
            .assertIsDisplayed()
    }

    @Test
    fun givenEmptyStateWhenScreenShownThenShowsEmptyViewWithoutSearchField() {
        composeRule.setContent {
            CountriesDemoTheme {
                CountriesListScreen(
                    state = ScreenUiState.Empty,
                    onRetry = {},
                    onCountryClick = {},
                    searchQuery = remember { mutableStateOf("") },
                    onSearchQueryChanged = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_empty_view))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_search_field))
            .assertDoesNotExist()
    }

    @Test
    fun givenSuccessWithDataWhenScreenShownThenShowsSearchFieldAndList() {
        val countries = PreviewData.sampleCountrySummaryList
        val firstCountryName = countries.first().commonName

        composeRule.setContent {
            CountriesDemoTheme {
                CountriesListScreen(
                    state = ScreenUiState.Success(
                        CountriesListSuccess.WithData(countries)
                    ),
                    onRetry = {},
                    onCountryClick = {},
                    searchQuery = remember { mutableStateOf("") },
                    onSearchQueryChanged = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_search_field))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_list))
            .assertIsDisplayed()

        composeRule.onNodeWithText(firstCountryName)
            .assertIsDisplayed()
    }

    @Test
    fun givenSuccessNoDataWhenScreenShownThenShowsNoResultsMessage() {
        val query = "ZZZ"
        val emptyResultsText = composeRule.activity.getString(
            R.string.search_query_empty_results,
            query
        )

        composeRule.setContent {
            CountriesDemoTheme {
                CountriesListScreen(
                    state = ScreenUiState.Success(CountriesListSuccess.NoData),
                    onRetry = {},
                    onCountryClick = {},
                    searchQuery = remember { mutableStateOf(query) },
                    onSearchQueryChanged = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_search_field))
            .assertIsDisplayed()

        composeRule.onNodeWithText(emptyResultsText)
            .assertIsDisplayed()

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_list))
            .assertDoesNotExist()
    }
}