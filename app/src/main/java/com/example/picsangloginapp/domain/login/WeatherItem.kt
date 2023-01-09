package com.example.picsangloginapp.domain.login

import com.best.core.exception.ExceptionType

sealed class WeatherItem {
    abstract fun <T> map(mapper: WeatherUiMapper<T>): T

    data class Basic(
        private val description: String,
        private val temp: Int,
        private val feelsLike: Int
    ) : WeatherItem() {
        override fun <T> map(mapper: WeatherUiMapper<T>) =
            mapper.map(feelsLike = feelsLike, description = description, temp = temp)
    }

    data class Error(private val exceptionType: ExceptionType = ExceptionType.GENERIC) :
        WeatherItem() {
        override fun <T> map(mapper: WeatherUiMapper<T>) = mapper.map(exceptionType = exceptionType)
    }
}

interface WeatherUiMapper<T> {

    fun map(feelsLike: Int, description: String, temp: Int): T

    fun map(exceptionType: ExceptionType): T
}
