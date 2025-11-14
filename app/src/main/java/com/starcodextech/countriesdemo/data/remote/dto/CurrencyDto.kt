package com.starcodextech.countriesdemo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyDto(
    val name: String? = "",
    val symbol: String? = ""
)