package com.sayas.weathercast.webservices

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class HttpClientConfig {

    @OptIn(ExperimentalSerializationApi::class)
    val httpClient = HttpClient {
        expectSuccess = true
        defaultRequest {
            url("https://api.openweathermap.org/data/2.5/")
            url{
                parameters.append("lat","35.7219")
                parameters.append("lon","51.3347")
                parameters.append("appid","bf0051926dd7564f4df2001a0a467d66")
                parameters.append("units","metric")
            }
        }
        install(Logging){
             logger = object : Logger{
                 override fun log(message: String) {
                     Napier.d(message, tag = "httpclient log")
                 }

             }
            level = LogLevel.ALL
        }
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
                coerceInputValues = true
            })
        }

    }
}