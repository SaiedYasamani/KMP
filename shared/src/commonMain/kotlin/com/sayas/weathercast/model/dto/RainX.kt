package com.sayas.weathercast.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 data class RainX(
    @SerialName("3h")
    val h: Double?
)