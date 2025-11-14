package com.starcodextech.countriesdemo.common.logger

interface Logger {
    fun e(tag: String, msg: String, tr: Throwable)
    fun d(tag: String, msg: String)
}