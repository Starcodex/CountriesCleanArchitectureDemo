package com.starcodextech.countriesdemo.domain.countries.detail.usecase

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.detail.repository.CountryDetailsRepository
import javax.inject.Inject

class CountryDetailsUseCaseImpl @Inject constructor(
    private val countryDetailsRepository: CountryDetailsRepository
) : CountryDetailsUseCase {
    override suspend fun invoke(countryName: String): AppResult<CountryDetails, AppError> =
        countryDetailsRepository.getCountryByName(countryName)
}