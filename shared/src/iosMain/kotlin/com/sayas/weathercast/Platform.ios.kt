package com.sayas.weathercast

import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getClientId(input: Any?): String? {
    return "${UIDevice.currentDevice.systemName}_${UIDevice.currentDevice.systemVersion}_${UIDevice.currentDevice.identifierForVendor}"
}