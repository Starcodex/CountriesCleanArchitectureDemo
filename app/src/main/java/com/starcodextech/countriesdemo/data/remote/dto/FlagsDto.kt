package com.starcodextech.countriesdemo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class FlagsDto(
    val png: String = "",
    val svg: String = "",
    val alt: String = ""
)