package com.starcodextech.countriesdemo.di

import com.starcodextech.countriesdemo.common.logger.AndroidLogger
import com.starcodextech.countriesdemo.common.logger.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoggerModule {

    @Binds
    @Singleton
    abstract fun bindAndroidLogger(
        impl: AndroidLogger
    ): Logger

}