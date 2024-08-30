package com.sayas.weathercast.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 data class CoordX(
    @SerialName("lat")
    val lat: Double?,
    @SerialName("lon")
    val lon: Double?
)