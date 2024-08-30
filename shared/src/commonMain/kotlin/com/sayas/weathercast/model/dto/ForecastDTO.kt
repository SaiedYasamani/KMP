package com.sayas.weathercast.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 data class ForecastDTO(
    @SerialName("city")
    val city: City?,
    @SerialName("cnt")
    val cnt: Int?,
    @SerialName("cod")
    val cod: String?,
    @SerialName("list")
    val forecast: List<Forecast>?,
    @SerialName("message")
    val message: Int?
)