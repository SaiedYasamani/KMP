package com.sayas.weathercast.model

import com.sayas.weathercast.model.dto.ForecastDTO
import com.sayas.weathercast.model.dto.WeatherDTO
import com.sayas.weathercast.webservices.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.request.get

class Repository {

    private val client = HttpClientConfig()

    suspend fun getWeather(): RestResult<WeatherDTO> {
        try {
            val response = client.httpClient.get("weather").body<WeatherDTO>()
            return RestResult.Success(response)
        } catch (e: Exception) {
            return RestResult.Error(e.message ?: "response error")
        }
    }

    suspend fun getForecast(): RestResult<ForecastDTO>{
        try {
            val response = client.httpClient.get("forecast").body<ForecastDTO>()
            return RestResult.Success(response)
        } catch (e: Exception) {
            return RestResult.Error(e.message ?: "response error")
        }
    }
}