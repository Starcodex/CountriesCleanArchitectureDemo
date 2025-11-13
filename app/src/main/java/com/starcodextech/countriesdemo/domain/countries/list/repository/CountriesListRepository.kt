package com.starcodextech.countriesdemo.domain.countries.list.repository

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary

interface CountriesListRepository {
    suspend fun getAllCountries(): AppResult<List<CountrySummary>, AppError>
}