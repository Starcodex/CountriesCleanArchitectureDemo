package com.starcodextech.countriesdemo.ui.common.mapper

import com.starcodextech.countriesdemo.R
import com.starcodextech.countriesdemo.common.error.AppError
import org.junit.Assert.assertEquals
import org.junit.Test

class UiErrorMapperTest {

    @Test
    fun `given Network error when toUiError then returns Network UiError`() {
        val appError = AppError.Network

        val uiError = appError.toUiError()

        assertEquals(R.string.ui_network_error, uiError.messageRes)
    }

    @Test
    fun `given NotFound error when toUiError then returns NotFound UiError`() {
        val appError = AppError.NotFound

        val uiError = appError.toUiError()

        assertEquals(R.string.ui_not_found_error, uiError.messageRes)
    }
}