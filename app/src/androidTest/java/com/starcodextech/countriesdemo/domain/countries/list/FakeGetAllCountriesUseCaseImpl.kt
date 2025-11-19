package com.starcodextech.countriesdemo.domain.countries.list

import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.AndroidDataTest.colombiaCountrySummaryExpected
import com.starcodextech.countriesdemo.data.AndroidDataTest.ukCountrySummaryExpected
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCase

object FakeGetAllCountriesUseCaseImpl : GetAllCountriesUseCase {

    var result: AppResult<List<CountrySummary>, AppError> =
        AppResult.Success(
            listOf(
                ukCountrySummaryExpected,
                colombiaCountrySummaryExpected
            )
        )

    override suspend fun invoke(): AppResult<List<CountrySummary>, AppError> = result
}