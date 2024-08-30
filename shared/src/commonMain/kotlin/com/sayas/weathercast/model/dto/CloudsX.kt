package com.sayas.weathercast.model.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 data class CloudsX(
    @SerialName("all")
    val all: Int?
)