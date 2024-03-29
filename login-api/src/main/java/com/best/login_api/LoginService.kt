package com.best.login_api

import com.best.login_api.models.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {

    @GET("weather")
    suspend fun getWeather(
        @Query("id") cityId: Int = 498817,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "metric"
    ): WeatherDto
}