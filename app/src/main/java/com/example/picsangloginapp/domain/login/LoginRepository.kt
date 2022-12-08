package com.example.picsangloginapp.domain.login

import com.example.picsangloginapp.data.login.WeatherDto

interface LoginRepository {

    suspend fun getWeather() : WeatherDto
}