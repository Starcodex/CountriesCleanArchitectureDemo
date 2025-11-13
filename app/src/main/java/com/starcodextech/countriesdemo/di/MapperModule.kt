package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.data.remote.mapper.CountryDetailsMapper
import com.starcodextech.countriesdemo.data.remote.mapper.CountrySummaryMapper
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindCountrySummaryMapper(
        impl: CountrySummaryMapper
    ): Mapper<CountryDto, CountrySummary>

    @Binds
    @Singleton
    abstract fun bindCountryDetailsMapper(
        impl: CountryDetailsMapper
    ): Mapper<CountryDto, CountryDetails>
}