package com.starcodextech.countriesdemo.data.remote.mapper

import com.starcodextech.countriesdemo.data.remote.dto.CurrencyDto

fun formattedCurrency(currencies: Map<String, CurrencyDto>? = null): String =
    if (currencies.isNullOrEmpty())
        "N/A"
    else currencies.entries.joinToString(", ") { (code, currency) ->
        "$code (${currency.name})"
    }