package com.starcodextech.countriesdemo.data.remote.mapper

fun formattedCapital(capital: List<String>? = null): String =
    if (capital.isNullOrEmpty())
        "N/A"
    else capital.joinToString(", ")