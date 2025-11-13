package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.ui.countries.list.mapper.CountrySummaryUiMapper
import com.starcodextech.countriesdemo.ui.countries.list.model.CountrySummaryUiModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UiMapperModule {

    @Binds
    abstract fun bindCountrySummaryUiMapper(
        impl: CountrySummaryUiMapper
    ): Mapper<CountrySummary, CountrySummaryUiModel>
}