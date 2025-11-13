package com.starcodextech.countriesdemo.domain.countries.list.usecase

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary

interface GetAllCountriesUseCase {
    suspend operator fun invoke(): AppResult<List<CountrySummary>, AppError>
}