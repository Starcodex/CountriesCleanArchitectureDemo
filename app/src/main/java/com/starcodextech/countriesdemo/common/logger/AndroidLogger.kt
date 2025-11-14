package com.starcodextech.countriesdemo.common.logger

import android.util.Log
import javax.inject.Inject

class AndroidLogger @Inject constructor() : Logger {

    override fun e(tag: String, msg: String, tr: Throwable) {
        Log.e(tag, msg, tr)
    }

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}