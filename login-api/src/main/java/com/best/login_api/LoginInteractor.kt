package com.best.login_api

interface LoginInteractor {

    suspend fun login() : WeatherItem
}