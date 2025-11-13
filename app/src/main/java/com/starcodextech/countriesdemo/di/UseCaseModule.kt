package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.domain.countries.detail.usecase.CountryDetailsUseCase
import com.starcodextech.countriesdemo.domain.countries.detail.usecase.CountryDetailsUseCaseImpl
import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCase
import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetAllCountriesUseCase(
        impl: GetAllCountriesUseCaseImpl
    ): GetAllCountriesUseCase

    @Binds
    @Singleton
    abstract fun bindCountryDetailsUseCase(
        impl: CountryDetailsUseCaseImpl
    ): CountryDetailsUseCase
}