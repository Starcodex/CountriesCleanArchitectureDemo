package com.starcodextech.countriesdemo.common.coroutines

import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AppDispatchersImplTest {

    private lateinit var dispatchers: AppDispatchersImpl

    @Before
    fun setUp() {
        dispatchers = AppDispatchersImpl()
    }

    @Test
    fun `given AppDispatchersImpl when io dispatcher requested then returns Dispatchers IO`() {
        val result = dispatchers.io
        assertEquals(Dispatchers.IO, result)
    }

    @Test
    fun `given AppDispatchersImpl when default dispatcher requested then returns Dispatchers Default`() {
        val result = dispatchers.default
        assertEquals(Dispatchers.Default, result)
    }
}