package com.starcodextech.countriesdemo.ui.countries.detail.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.starcodextech.countriesdemo.common.navigation.BaseDestination
import com.starcodextech.countriesdemo.ui.countries.detail.navigation.CountryDetailsNavigation.COUNTRY_NAME
import com.starcodextech.countriesdemo.ui.countries.detail.view.DetailsRoute
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState

object CountryDetailsNavigation : BaseDestination {
    const val COUNTRY_NAME = "country_name"

    override val route: String = "details_route"
    override val destination: String = "details_destination/{$COUNTRY_NAME}"

}

fun NavGraphBuilder.countryDetailsGraph(
    topBarState: MutableState<TopBarUiState>
) {
    navigation(
        route = CountryDetailsNavigation.route,
        startDestination = CountryDetailsNavigation.destination
    ){
        composable(
            route = CountryDetailsNavigation.fullDestination(COUNTRY_NAME),
            arguments = listOf(
                navArgument(COUNTRY_NAME) {
                    defaultValue = ""
                    type =  NavType.StringType
                }
            )
        ) {
            DetailsRoute(
                topBarState = topBarState
            )
        }
    }
}
