package com.best.login_impl

import com.best.core.exception.ExceptionHandler
import com.best.login_api.LoginInteractor
import com.best.login_api.LoginRepository
import com.best.login_api.WeatherItem
import javax.inject.Inject


internal class LoginInteractorImpl @Inject constructor(
    private val repository: LoginRepository,
    private val exceptionHandler: ExceptionHandler
) : LoginInteractor {
    override suspend fun login() = try {
        repository.getWeather()
    } catch (e: Exception) {
        WeatherItem.Error(exceptionType = exceptionHandler.defineExceptionType(e))
    }
}