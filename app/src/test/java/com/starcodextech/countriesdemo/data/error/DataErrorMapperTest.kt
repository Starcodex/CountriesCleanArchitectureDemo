package com.starcodextech.countriesdemo.data.error

import com.starcodextech.countriesdemo.common.error.AppError
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class DataErrorMapperTest {

    @Test
    fun `given IOException when toAppError then returns Network`() {
        val throwable = IOException("network down")
        val error = throwable.toAppError()
        assertEquals(AppError.Network, error)
    }

    @Test
    fun `given Http 401 when toAppError then returns Unauthorized`() {
        val throwable = httpException(code = 401)
        val error = throwable.toAppError()
        assertEquals(AppError.Unauthorized, error)
    }

    @Test
    fun `given Http 404 when toAppError then returns NotFound`() {
        val throwable = httpException(code = 404)
        val error = throwable.toAppError()
        assertEquals(AppError.NotFound, error)
    }

    @Test
    fun `given Http 500 when toAppError then returns ServerError`() {
        val throwable = httpException(code = 500)
        val error = throwable.toAppError()
        assertEquals(AppError.ServerError, error)
    }

    @Test
    fun `given generic Exception when toAppError then returns Unknown`() {
        val throwable = IllegalStateException("boom")
        val error = throwable.toAppError()
        assertTrue(error is AppError.Unknown)
    }

    private fun httpException(code: Int): HttpException {
        val body = ResponseBody.create(
            "application/json".toMediaType(),
            """{"error":"$code"}"""
        )
        val response = Response.error<Any>(code, body)
        return HttpException(response)
    }
}