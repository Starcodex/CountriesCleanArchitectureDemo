package com.starcodextech.countriesdemo.domain.countries.detail.repository

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails

interface CountryDetailsRepository {
    suspend fun getCountryByCode(code: String): AppResult<CountryDetails, AppError>
}