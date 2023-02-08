package com.best.login_api.models

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("weather")
    val weatherInnerDto: List<WeatherInnerDto>,
    @SerializedName("main")
    val main: WeatherMainInfoDto
)

data class WeatherInnerDto(
    @SerializedName("description")
    val description: String
)

data class WeatherMainInfoDto(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLike: Float
)