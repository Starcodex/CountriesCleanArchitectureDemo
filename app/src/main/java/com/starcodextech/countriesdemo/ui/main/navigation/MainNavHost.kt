package com.starcodextech.countriesdemo.ui.main.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.ui.components.CountriesTopBar
import com.starcodextech.countriesdemo.ui.countries.detail.navigation.CountryDetailsNavigation
import com.starcodextech.countriesdemo.ui.countries.detail.navigation.CountryDetailsNavigation.COUNTRY_NAME
import com.starcodextech.countriesdemo.ui.countries.detail.navigation.CountryDetailsNavigation.destinationWithArguments
import com.starcodextech.countriesdemo.ui.countries.detail.navigation.countryDetailsGraph
import com.starcodextech.countriesdemo.ui.countries.list.navigation.CountriesListNavigation
import com.starcodextech.countriesdemo.ui.countries.list.navigation.countriesListGraph
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = CountriesListNavigation.route
) {
    val topBarState = remember {
        mutableStateOf(
            TopBarUiState(
                titleRes = R.string.ctop_bar_title,
                showBack = false
            )
        )
    }

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            CountriesTopBar(
                topBarState = topBarState.value,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            countriesListGraph(
                navigateToDetailsScreen = { countryName ->
                    navController.navigate(
                        destinationWithArguments(
                            COUNTRY_NAME to countryName
                        )
                    )
                },
                topBarState = topBarState
            )

            countryDetailsGraph(
                onBackClick = { navController.popBackStack() },
                topBarState = topBarState
            )
        }
    }
}
