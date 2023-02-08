package com.best.login_feature

import com.best.core.exception.ExceptionType
import com.best.core.resourcemanager.ResourceManager
import com.best.login_api.models.WeatherUiMapper
import com.best.login_feature.models.WeatherUiModel

internal class WeatherUiMapperImpl(private val resourceManager: ResourceManager) :
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