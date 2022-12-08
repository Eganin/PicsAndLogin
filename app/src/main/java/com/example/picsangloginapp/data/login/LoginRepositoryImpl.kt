package com.example.picsangloginapp.data.login

import com.example.picsangloginapp.domain.login.LoginRepository

class LoginRepositoryImpl(private val service : LoginService): LoginRepository {
    override suspend fun getWeather()= service.getWeather()
}