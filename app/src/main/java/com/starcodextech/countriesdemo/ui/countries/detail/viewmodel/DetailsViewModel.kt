package com.starcodextech.countriesdemo.ui.countries.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.starcodextech.countriesdemo.ui.countries.detail.navigation.CountryDetailsNavigation.COUNTRY_NAME
import com.starcodextech.countriesdemo.ui.main.state.TopBarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedState: SavedStateHandle
) : ViewModel() {

    private val countryName: String =
        checkNotNull(savedState[COUNTRY_NAME]) {
            "COUNTRY_NAME arg is required"
        }

    private val _topBarState = MutableStateFlow(
        TopBarUiState(
            titleText = countryName,
            showBack = true
        )
    )
    val topBarState: StateFlow<TopBarUiState> = _topBarState


}