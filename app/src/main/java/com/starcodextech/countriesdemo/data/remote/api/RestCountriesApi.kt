package com.starcodextech.countriesdemo.data.remote.api

import com.starcodextech.countriesdemo.data.remote.api.RestCountriesApiConfig.FIELDS
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestCountriesApi {
    @GET("v3.1/all")
    suspend fun getAllCountries(
        @Query("fields")
        fields: String = FIELDS
    ): List<CountryDto>

    @GET("v3.1/name/{countryName}")
    suspend fun getCountryByName(
        @Path("countryName")
        countryName: String,
        @Query("fields")
        fields: String = FIELDS,
        @Query("fullText")
        fullText: Boolean = true
    ): List<CountryDto>
}