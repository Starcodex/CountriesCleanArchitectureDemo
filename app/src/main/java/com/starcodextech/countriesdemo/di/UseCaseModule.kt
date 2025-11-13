package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCase
import com.starcodextech.countriesdemo.domain.countries.list.usecase.GetAllCountriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetAllCountriesUseCase(
        impl: GetAllCountriesUseCaseImpl
    ): GetAllCountriesUseCase
}