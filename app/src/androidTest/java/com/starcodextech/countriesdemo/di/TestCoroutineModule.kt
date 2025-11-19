package com.starcodextech.countriesdemo.di

import dagger.Module
import dagger.Provides
import com.starcodextech.countriesdemo.common.coroutines.AppDispatchers
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutineModule::class]
)
object TestCoroutineModule {

    @Provides
    @Singleton
    fun provideTestDispatchers(): AppDispatchers = object : AppDispatchers {
        override val io: CoroutineDispatcher = Dispatchers.Main
        override val main: CoroutineDispatcher = Dispatchers.Main
        override val default: CoroutineDispatcher = Dispatchers.Main
    }
}