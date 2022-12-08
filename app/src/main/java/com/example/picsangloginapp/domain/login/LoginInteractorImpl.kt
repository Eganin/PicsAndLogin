package com.example.picsangloginapp.domain.login

import com.example.picsangloginapp.core.exception.ExceptionHandler
import com.example.picsangloginapp.core.mapper.Mapper
import com.example.picsangloginapp.data.login.WeatherDto

class LoginInteractorImpl(
    private val repository: LoginRepository,
    private val mapper: Mapper<WeatherItem, WeatherDto>,
    private val exceptionHandler: ExceptionHandler
) : LoginInteractor {
    override suspend fun login() = try {
        mapper.map(source = repository.getWeather())
    } catch (e: Exception) {
        WeatherItem.Error(exceptionType = exceptionHandler.defineExceptionType(e))
    }
}