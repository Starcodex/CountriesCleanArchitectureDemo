package com.starcodextech.countriesdemo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val name: NameDto,
    val capital: List<String> = emptyList(),
    val cca3: String,
    val region: String,
    val subregion: String? = null,
    val languages: Map<String, String> = emptyMap(),
    val currencies: Map<String, CurrencyDto> = emptyMap(),
    val population: Long,
    val car: CarDto,
    val flags: FlagsDto
)