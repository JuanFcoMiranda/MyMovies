package com.jfma75.mymovies.ui.common

import android.util.Log

interface ILogger {
    val customTag : String
        get() = javaClass.simpleName

    fun logDebug(message: String) = Log.d(customTag, message)
    fun logInfo(message: String) = Log.i(customTag, message)
    fun logError(message: String) = Log.e(customTag, message)
    fun logWarning(message: String) = Log.w(customTag, message)
}