package com.starcodextech.countriesdemo.ui.countries.detail.view

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.common.error.UiError
import com.starcodextech.countriesdemo.ui.common.state.ScreenUiState
import com.starcodextech.countriesdemo.ui.countries.detail.model.CountryDetailsUiModel
import com.starcodextech.countriesdemo.ui.preview.PreviewData
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun givenLoadingStateWhenScreenShownThenShowsLoadingView() {

        composeRule.setContent {
            CountriesDemoTheme {
                DetailsScreen(
                    state = ScreenUiState.Loading,
                    loadCountryDetails = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_loading_view))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_detail_view))
            .assertDoesNotExist()
    }

    @Test
    fun givenErrorStateWhenScreenShownThenShowsErrorMessageAndRetryButton() {
        val errorText = composeRule.activity.getString(R.string.ui_generic_error)

        composeRule.setContent {
            CountriesDemoTheme {
                DetailsScreen(
                    state = ScreenUiState.Error(uiError = UiError.Generic()),
                    loadCountryDetails = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_error_view))
            .assertIsDisplayed()

        composeRule.onNodeWithText(errorText)
            .assertIsDisplayed()
    }

    @Test
    fun givenEmptyStateWhenScreenShownThenShowsEmptyView() {

        composeRule.setContent {
            CountriesDemoTheme {
                DetailsScreen(
                    state = ScreenUiState.Empty,
                    loadCountryDetails = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_empty_view))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_detail_view))
            .assertDoesNotExist()
    }

    @Test
    fun givenSuccessWhenScreenShownThenShowsDetailsView() {

        val country = PreviewData.ukDetails
        val firstCountryName = country.commonName

        composeRule.setContent {
            CountriesDemoTheme {
                DetailsScreen(
                    state = ScreenUiState.Success<CountryDetailsUiModel>(content = country),
                    loadCountryDetails = {}
                )
            }
        }

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_countries_detail_view))
            .assertIsDisplayed()

        composeRule.onNodeWithText(firstCountryName)
            .assertIsDisplayed()
    }

}