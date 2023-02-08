package com.best.login_api

import com.best.login_api.models.WeatherItem


interface LoginRepository {

    suspend fun getWeather() : WeatherItem
}