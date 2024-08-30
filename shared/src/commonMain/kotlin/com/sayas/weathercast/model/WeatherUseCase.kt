package com.sayas.weathercast.model

import com.sayas.weathercast.model.dto.ForecastDTO
import com.sayas.weathercast.model.dto.WeatherDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class WeatherUseCase {

    private val repository = Repository()

    suspend operator fun invoke(dispatcher: CoroutineDispatcher = Dispatchers.IO): RestResult<Pair<WeatherDTO,ForecastDTO>>{
        return coroutineScope{
            withContext(context = dispatcher){
             try {
                    val weather = async { repository.getWeather() }.await() as RestResult.Success
                    val forecast = async { repository.getForecast() }.await() as RestResult.Success
                    RestResult.Success(Pair(weather.data,forecast.data))
                } catch (e: Exception) {
                    RestResult.Error(e.message ?: "")
                }
            }
        }
    }
}