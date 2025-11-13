package com.starcodextech.countriesdemo.ui.main.state

import androidx.annotation.StringRes

data class TopBarUiState(
    @StringRes val titleRes: Int? = null,
    val titleText: String? = null,
    val showBack: Boolean = false
)