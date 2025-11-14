package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.data.repository.country.details.CountryDetailsRepositoryImpl
import com.starcodextech.countriesdemo.data.repository.country.list.CountriesListRepositoryImpl
import com.starcodextech.countriesdemo.domain.countries.detail.repository.CountryDetailsRepository
import com.starcodextech.countriesdemo.domain.countries.list.repository.CountriesListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCountryListRepository(
        impl: CountriesListRepositoryImpl
    ): CountriesListRepository

    @Binds
    @Singleton
    abstract fun bindCountryDetailsRepository(
        impl: CountryDetailsRepositoryImpl
    ): CountryDetailsRepository

}