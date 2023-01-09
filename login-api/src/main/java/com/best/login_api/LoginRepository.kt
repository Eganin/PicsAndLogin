package com.best.login_api


interface LoginRepository {

    suspend fun getWeather() : WeatherItem
}