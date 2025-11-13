package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.domain.countries.detail.model.CountryDetails
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.ui.countries.detail.mapper.CountryDetailsUiMapper
import com.starcodextech.countriesdemo.ui.countries.detail.model.CountryDetailsUiModel
import com.starcodextech.countriesdemo.ui.countries.list.mapper.CountrySummaryUiMapper
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UiMapperModule {

    @Binds
    @Singleton
    abstract fun bindCountryDetailsUiMapper(
        impl: CountryDetailsUiMapper
    ): Mapper<CountryDetails, CountryDetailsUiModel>

    @Binds
    @Singleton
    abstract fun bindCountrySummaryUiMapper(
        impl: CountrySummaryUiMapper
    ): Mapper<CountrySummary, CountrySummaryUiModel>
}