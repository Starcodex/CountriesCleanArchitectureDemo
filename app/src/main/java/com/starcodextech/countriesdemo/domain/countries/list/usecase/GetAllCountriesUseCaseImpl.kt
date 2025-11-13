package com.starcodextech.countriesdemo.domain.countries.list.usecase

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.repository.CountriesListRepository
import javax.inject.Inject

class GetAllCountriesUseCaseImpl @Inject constructor(
    private val repository: CountriesListRepository
): GetAllCountriesUseCase {
    override suspend fun invoke(): AppResult<List<CountrySummary>, AppError> =
        repository.getAllCountries()
}