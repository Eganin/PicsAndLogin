package com.example.picsangloginapp.ui.login

import com.example.picsangloginapp.R
import com.example.picsangloginapp.core.exception.ExceptionType
import com.example.picsangloginapp.core.resourcemanager.ResourceManager
import com.example.picsangloginapp.domain.login.WeatherUiMapper

class WeatherUiMapperImpl(private val resourceManager: ResourceManager) :
    WeatherUiMapper<WeatherUiModel> {
    override fun map(feelsLike: Int, description: String, temp: Int)=
        WeatherUiModel(
            description= resourceManager.getString(
                R.string.weather_description,
                description,
                temp,
                feelsLike
            )
        )

    override fun map(exceptionType: ExceptionType) =
        WeatherUiModel(
            description = resourceManager.getErrorMessage(exceptionType = exceptionType),
            isError = true
        )
}