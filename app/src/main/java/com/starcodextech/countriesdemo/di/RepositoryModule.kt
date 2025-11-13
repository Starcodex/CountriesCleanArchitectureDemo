package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.data.remote.dto.CountryDto
import com.starcodextech.countriesdemo.data.remote.mapper.CountrySummaryMapper
import com.starcodextech.countriesdemo.data.repository.country.list.CountriesListRepositoryImpl
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.repository.CountriesListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCountryListRepository(
        impl: CountriesListRepositoryImpl
    ): CountriesListRepository

    @Binds
    abstract fun bindCountrySummaryMapper(
        impl: CountrySummaryMapper
    ): Mapper<CountryDto, CountrySummary>
}