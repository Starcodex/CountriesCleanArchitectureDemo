package com.starcodextech.countriesdemo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val name: NameDto? = NameDto(),
    val capital: List<String>? = emptyList(),
    val cca3: String? = "",
    val region: String? = "",
    val subregion: String? = "",
    val languages: Map<String, String>? = emptyMap(),
    val currencies: Map<String, CurrencyDto>? = emptyMap(),
    val population: Long? = 0,
    val car: CarDto? = CarDto(),
    val flags: FlagsDto? = FlagsDto()
)