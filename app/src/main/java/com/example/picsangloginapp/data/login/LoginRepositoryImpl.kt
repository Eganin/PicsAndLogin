package com.example.picsangloginapp.data.login

import com.example.picsangloginapp.domain.login.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val service: LoginService) : LoginRepository {
    override suspend fun getWeather() = service.getWeather()
}