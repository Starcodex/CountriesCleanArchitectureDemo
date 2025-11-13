package com.starcodextech.countriesdemo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NativeNameDto(
    val official: String,
    val common: String
)