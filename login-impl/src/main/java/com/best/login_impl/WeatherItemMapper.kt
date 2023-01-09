package com.best.login_impl

import com.best.core.mapper.Mapper
import com.best.login_api.WeatherItem

class WeatherItemMapper : Mapper<WeatherItem, WeatherDto> {

    override fun map(source: WeatherDto) = when {
        source.weatherInnerDto.isEmpty() -> WeatherItem.Error()
        source.weatherInnerDto[0].description.isEmpty() -> WeatherItem.Error()
        else -> WeatherItem.Basic(
            description = source.weatherInnerDto[0].description,
            temp = source.main.temp.toInt(),
            feelsLike = source.main.feelsLike.toInt()
        )
    }
}