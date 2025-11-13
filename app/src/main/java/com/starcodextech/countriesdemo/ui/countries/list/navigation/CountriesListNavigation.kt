package com.starcodextech.countriesdemo.ui.countries.list.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.starcodextech.countriesdemo.common.navigation.BaseDestination
import com.starcodextech.countriesdemo.ui.countries.list.view.CountriesListRoute
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState

object CountriesListNavigation : BaseDestination {
    override val route: String = "countries_route"
    override val destination: String = "countries_destination"
}

fun NavGraphBuilder.countriesListGraph(
    navigateToDetailsScreen: (String) -> Unit,
    topBarState: MutableState<TopBarUiState>
){
    navigation(
        route = CountriesListNavigation.route,
        startDestination = CountriesListNavigation.destination
    ){
        composable(CountriesListNavigation.destination) { entry ->
            CountriesListRoute(
                onCountryClick = navigateToDetailsScreen,
                topBarState = topBarState
            )
        }
    }
}