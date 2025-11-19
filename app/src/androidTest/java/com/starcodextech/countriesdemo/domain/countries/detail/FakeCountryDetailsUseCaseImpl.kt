package com.starcodextech.countriesdemo.domain.countries.detail

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.AndroidDataTest.ukCountryDetailsExpected
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.detail.usecase.CountryDetailsUseCase

object FakeCountryDetailsUseCaseImpl : CountryDetailsUseCase {

    var result: AppResult<CountryDetails, AppError> =
        AppResult.Success(ukCountryDetailsExpected)
    override suspend fun invoke(countryName: String): AppResult<CountryDetails, AppError> = result
}