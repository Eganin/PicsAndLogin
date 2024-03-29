package com.best.login_impl

import com.best.core.mapper.Mapper
import com.best.login_api.LoginRepository
import com.best.login_api.LoginService
import com.best.login_api.models.WeatherDto
import com.best.login_api.models.WeatherItem
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val service: LoginService,
    private val mapper: Mapper<WeatherItem, WeatherDto>,
) : LoginRepository {
    override suspend fun getWeather() = mapper.map(source = service.getWeather())
}