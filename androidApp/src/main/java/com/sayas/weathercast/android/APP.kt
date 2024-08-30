package com.sayas.weathercast.android

import android.app.Application
import com.sayas.weathercast.NapierHelper

class APP: Application() {

    override fun onCreate() {
        super.onCreate()
        NapierHelper().initialNapier()
    }
}