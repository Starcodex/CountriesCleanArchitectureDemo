package com.starcodextech.countriesdemo.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher

class TestDispatchers(
    private val dispatcher: CoroutineDispatcher
) : AppDispatchers {
    override val io: CoroutineDispatcher = dispatcher
    override val main: CoroutineDispatcher = dispatcher
    override val default: CoroutineDispatcher = dispatcher
}