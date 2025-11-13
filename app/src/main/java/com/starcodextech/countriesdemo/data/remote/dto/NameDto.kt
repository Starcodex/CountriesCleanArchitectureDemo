package com.starcodextech.countriesdemo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NameDto(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeNameDto> = emptyMap()
)