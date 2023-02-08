package com.best.login_api

import com.best.login_api.models.WeatherItem

interface LoginInteractor {

    suspend fun login() : WeatherItem
}