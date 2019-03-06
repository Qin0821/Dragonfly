package com.oed.commons.utils

import android.util.Log
import android.util.Log.*
import java.lang.Error

fun Throwable.log(tag: String, level: Int = ERROR) {
    when (level) {
        VERBOSE -> Log.v(tag, this.toString())
        DEBUG -> Log.d(tag, this.toString())
        INFO -> Log.i(tag, this.toString())
        WARN -> Log.w(tag, this.toString())
        ERROR -> Log.e(tag, this.toString())
    }
}

fun Exception.log(tag: String, level: Int = ERROR) {
    when (level) {
        VERBOSE -> Log.v(tag, this.toString())
        DEBUG -> Log.d(tag, this.toString())
        INFO -> Log.i(tag, this.toString())
        WARN -> Log.w(tag, this.toString())
        ERROR -> Log.e(tag, this.toString())

    }
}
