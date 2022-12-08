package com.example.picsangloginapp.domain.login

interface LoginInteractor {

    suspend fun login() : WeatherItem
}