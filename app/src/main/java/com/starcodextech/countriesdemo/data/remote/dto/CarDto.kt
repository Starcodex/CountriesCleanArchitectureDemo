package com.starcodextech.countriesdemo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CarDto(
    val signs: List<String> = emptyList(),
    val side: String = ""
)