package com.starcodextech.countriesdemo.data.repository.country.list

import android.util.Log
import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.error.toAppError
import com.starcodextech.countriesdemo.data.remote.api.RestCountriesApi
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.repository.CountriesListRepository
import javax.inject.Inject

class CountriesListRepositoryImpl  @Inject constructor(
    private val api: RestCountriesApi,
    private val mapper: Mapper<CountryDto, CountrySummary>
) : CountriesListRepository {

    override suspend fun getAllCountries(): AppResult<List<CountrySummary>, AppError> {
        return try {
            Log.d("CountryRepo", "getAllCountries() - calling API")
            val dtos = api.getAllCountries()
            Log.d("CountryRepo", "getAllCountries() - API returned ${dtos.size} items")

            val summaries = dtos.map { mapper.map(it) }
            Log.d("CountryRepo", "getAllCountries() - mapped ${summaries.size} items")

            AppResult.Success(summaries)
        } catch (t: Throwable) {
            Log.e("CountryRepo", "getAllCountries() - error calling API", t)
            AppResult.Error(t.toAppError())
        }
    }
}