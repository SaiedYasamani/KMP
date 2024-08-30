package com.sayas.weathercast

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getClientId(input:Any?): String?