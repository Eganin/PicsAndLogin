package com.example.picsangloginapp.domain.login

interface LoginRepository {

    suspend fun getWeather() : WeatherDto
}