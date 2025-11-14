package com.starcodextech.countriesdemo.common.logger

class FakeLogger : Logger {

    val logs = mutableListOf<String>()

    override fun e(tag: String, msg: String, tr: Throwable) {
        logs.add("$tag: $msg")
    }

    override fun d(tag: String, msg: String) {
        logs.add("$tag: $msg")
    }
}