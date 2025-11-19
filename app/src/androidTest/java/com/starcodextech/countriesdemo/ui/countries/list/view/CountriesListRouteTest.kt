package com.starcodextech.countriesdemo.ui.countries.list.view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.AndroidDataTest.colombiaCountrySummaryExpected
import com.starcodextech.countriesdemo.data.AndroidDataTest.ukCountryDetailsExpected
import com.starcodextech.countriesdemo.data.AndroidDataTest.ukCountrySummaryExpected
import com.starcodextech.countriesdemo.domain.countries.detail.FakeCountryDetailsUseCaseImpl
import com.starcodextech.countriesdemo.domain.countries.list.FakeGetAllCountriesUseCaseImpl
import com.starcodextech.countriesdemo.ui.main.navigation.MainNavHost
import com.starcodextech.countriesdemo.ui.main.TestMainActivity
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState
import com.starcodextech.countriesdemo.ui.theme.CountriesDemoTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CountriesListRouteTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<TestMainActivity>()

    // Resources & Tags
    private lateinit var errorNetworkMsg: String
    private lateinit var titleHome: String

    private lateinit var tagList: String
    private lateinit var tagErrorView: String
    private lateinit var tagEmptyView: String
    private lateinit var tagSearchField: String
    private lateinit var tagTopBarTitle: String
    private lateinit var tagBackButton: String

    @Before
    fun setup() {
        hiltRule.inject()
        initializeResources()
        resetFakes()
    }

    @Test
    fun givenSuccessResult_whenLaunch_thenShowsSearchFieldAndCountriesList() {
        // Arrange
        val expectedCountryName = ukCountrySummaryExpected.commonName
        FakeGetAllCountriesUseCaseImpl.result = AppResult.Success(
            listOf(ukCountrySummaryExpected, colombiaCountrySummaryExpected)
        )

        // Act
        launchCountriesScreen()

        // Assert
        composeRule.waitForNodeWithTag(tagList)

        composeRule.onNodeWithTag(tagSearchField).assertIsDisplayed()
        composeRule.onNodeWithText(expectedCountryName).assertIsDisplayed()
    }

    @Test
    fun givenErrorResult_whenLaunch_thenShowsErrorView() {
        // Arrange
        FakeGetAllCountriesUseCaseImpl.result = AppResult.Error(AppError.Network)

        // Act
        launchCountriesScreen()

        // Assert
        composeRule.waitForNodeWithTag(tagErrorView)

        composeRule.onNodeWithTag(tagErrorView).assertIsDisplayed()
        composeRule.onNodeWithText(errorNetworkMsg).assertIsDisplayed()
        composeRule.onNodeWithTag(tagList).assertDoesNotExist()
    }

    @Test
    fun givenEmptyResult_whenLaunch_thenShowsEmptyViewWithoutList() {
        // Arrange
        FakeGetAllCountriesUseCaseImpl.result = AppResult.Success(emptyList())

        // Act
        launchCountriesScreen()

        // Assert
        composeRule.waitForNodeWithTag(tagEmptyView)

        composeRule.onNodeWithTag(tagEmptyView).assertIsDisplayed()
        composeRule.onNodeWithTag(tagList).assertDoesNotExist()
    }

    @Test
    fun givenList_whenClickItem_thenNavigatesToDetail_verifiesTitle_andReturns() {
        // Arrange
        val countryName = ukCountrySummaryExpected.commonName
        FakeGetAllCountriesUseCaseImpl.result = AppResult.Success(
            listOf(ukCountrySummaryExpected, colombiaCountrySummaryExpected)
        )
        FakeCountryDetailsUseCaseImpl.result = AppResult.Success(ukCountryDetailsExpected)

        // Act
        launchMainGraph()

        // Assert - Initial State
        composeRule.onNodeWithText(titleHome).assertIsDisplayed()
        composeRule.onNodeWithText(countryName).assertIsDisplayed()

        // Act - Navigate to Details
        composeRule.onNodeWithText(countryName).performClick()
        composeRule.waitForIdle()

        // Assert - Detail Screen
        composeRule.onNodeWithTag(tagTopBarTitle)
            .assertIsDisplayed()
            .assertTextEquals(countryName)

        composeRule.onNodeWithTag(tagBackButton).assertIsDisplayed()

        // Act - Navigate Back
        composeRule.onNodeWithTag(tagBackButton).performClick()
        composeRule.waitForIdle()

        composeRule.onNodeWithText(titleHome).assertIsDisplayed()
        composeRule.onNodeWithText(countryName).assertIsDisplayed()
    }


    private fun initializeResources() {
        val activity = composeRule.activity
        errorNetworkMsg = activity.getString(R.string.ui_network_error)
        titleHome = activity.getString(R.string.ctop_bar_title)

        tagList = activity.getString(R.string.tag_countries_list)
        tagErrorView = activity.getString(R.string.tag_countries_error_view)
        tagEmptyView = activity.getString(R.string.tag_countries_empty_view)
        tagSearchField = activity.getString(R.string.tag_countries_search_field)
        tagTopBarTitle = activity.getString(R.string.tag_countries_top_bar_title)
        tagBackButton = activity.getString(R.string.tag_countries_back_button)
    }

    private fun resetFakes() {
        FakeGetAllCountriesUseCaseImpl.result = AppResult.Success(emptyList())
    }

    private fun launchCountriesScreen() {
        composeRule.setContent {
            CountriesDemoTheme {
                val topBarState = remember { mutableStateOf(TopBarUiState()) }
                CountriesListRoute(
                    onCountryClick = {},
                    topBarState = topBarState
                )
            }
        }
    }

    private fun launchMainGraph() {
        composeRule.setContent {
            CountriesDemoTheme {
                MainNavHost()
            }
        }
    }

    private fun ComposeTestRule.waitForNodeWithTag(
        testTag: String,
        timeoutMillis: Long = 5_000
    ) {
        waitUntil(timeoutMillis) {
            onAllNodesWithTag(testTag).fetchSemanticsNodes().isNotEmpty()
        }
    }

}