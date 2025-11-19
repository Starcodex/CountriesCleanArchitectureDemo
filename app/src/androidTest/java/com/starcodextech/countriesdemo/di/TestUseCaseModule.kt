package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.domain.countries.detail.FakeCountryDetailsUseCaseImpl
import com.starcodextech.countriesdemo.domain.countries.detail.usecase.CountryDetailsUseCase
import dagger.Module
import dagger.Provides
import com.starcodextech.countriesdemo.domain.countries.list.FakeGetAllCountriesUseCaseImpl
import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCase
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [
        UseCaseModule::class
    ]
)
object TestUseCaseModule {

    @Provides
    @Singleton
    fun provideFakeGetAllCountriesUseCase(): GetAllCountriesUseCase = FakeGetAllCountriesUseCaseImpl

    @Provides
    @Singleton
    fun provideCountryDetailsUseCase(): CountryDetailsUseCase = FakeCountryDetailsUseCaseImpl
}