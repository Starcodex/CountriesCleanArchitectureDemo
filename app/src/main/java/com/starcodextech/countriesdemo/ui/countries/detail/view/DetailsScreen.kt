package com.starcodextech.countriesdemo.ui.countries.detail.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.starcodextech.countriesdemo.ui.components.ProvideTopBarState
import com.starcodextech.countriesdemo.ui.countries.detail.viewmodel.DetailsViewModel
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState

@Composable
fun DetailsRoute(
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClick: () -> Boolean,
    topBarState: MutableState<TopBarUiState>
) {

    val vmTopBarState by viewModel.topBarState.collectAsState()

    ProvideTopBarState(
        topBarState = topBarState,
        override = vmTopBarState
    )



}