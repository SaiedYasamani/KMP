package com.sayas.weathercast

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class NapierHelper {

    fun initialNapier() {
        Napier.base(DebugAntilog())
    }

    fun printLogLevels() {
        Napier.d("napier debug level", tag = "napier log levels")
        Napier.i("napier info level", tag = "napier log levels")
        Napier.w("napier warning level", tag = "napier log levels")
        Napier.v("napier verbose level", tag = "napier log levels")
        Napier.e("napier error level", tag = "napier log levels")
    }
}