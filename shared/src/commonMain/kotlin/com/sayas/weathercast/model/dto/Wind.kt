package com.sayas.weathercast.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 data class Wind(
    @SerialName("deg")
    val deg: Int?,
    @SerialName("gust")
    val gust: Double?,
    @SerialName("speed")
    val speed: Double?
)