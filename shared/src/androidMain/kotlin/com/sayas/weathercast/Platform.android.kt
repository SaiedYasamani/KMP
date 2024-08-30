package com.sayas.weathercast

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Suppress("DEPRECATION")
@SuppressLint("MissingPermission", "HardwareIds")
actual fun getClientId(input: Any?): String? {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        val manager: TelephonyManager? =
            (input as Context).getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
        return manager?.deviceId
    } else {
        return Settings.Secure.getString(
            (input as Context).contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }
}