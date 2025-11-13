package com.starcodextech.countriesdemo.domain.countries.detail.usecase

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails

interface CountryDetailsUseCase {
    suspend operator fun invoke(countryName: String): AppResult<CountryDetails, AppError>
}