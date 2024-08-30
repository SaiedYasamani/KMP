package com.sayas.weathercast.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 data class Forecast (
    @SerialName("clouds")
    val clouds: CloudsX?,
    @SerialName("dt")
    val dt: Int?,
    @SerialName("dt_txt")
    val dtTxt: String?,
    @SerialName("main")
    val main: MainX?,
    @SerialName("pop")
    val pop: Double?,
    @SerialName("rain")
    val rain: RainX?,
    @SerialName("sys")
    val sys: SysX?,
    @SerialName("visibility")
    val visibility: Int?,
    @SerialName("weather")
    val weather: List<WeatherX>?,
    @SerialName("wind")
    val wind: WindX?
)