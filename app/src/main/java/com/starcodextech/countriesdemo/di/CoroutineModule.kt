package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.common.coroutines.AppDispatchers
import com.starcodextech.countriesdemo.common.coroutines.AppDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineModule {

    @Binds
    @Singleton
    abstract fun bindAppDispatchers(
        impl: AppDispatchersImpl
    ): AppDispatchers

}