package com.starcodextech.countriesdemo.data.repository.country.details

import android.util.Log
import com.starcodextech.countriesdemo.common.error.AppError
import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.common.result.AppResult
import com.starcodextech.countriesdemo.data.error.toAppError
import com.starcodextech.countriesdemo.data.remote.api.RestCountriesApi
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.detail.repository.CountryDetailsRepository
import javax.inject.Inject

class CountryDetailsRepositoryImpl @Inject constructor(
    private val api: RestCountriesApi,
    private val mapper: Mapper<CountryDto, CountryDetails>
) : CountryDetailsRepository {

    private val TAG = this.javaClass.name

    override suspend fun getCountryByName(name: String): AppResult<CountryDetails, AppError> {
        return try {
            Log.d(TAG, "getCountryByName($name) - calling API")

            val result = api.getCountryByName(name)
            Log.d(TAG, "getCountryByName() - API returned ${result.size} items")

            val dto = result.firstOrNull() ?: throw Exception("Country not found")

            val country = mapper.map(dto)
            Log.d(TAG, "getCountryByName() - mapped $country")

            AppResult.Success(country)
        } catch (t: Throwable) {
            Log.e(TAG, "getCountryByName() - error calling API", t)
            AppResult.Error(t.toAppError())
        }
    }
}