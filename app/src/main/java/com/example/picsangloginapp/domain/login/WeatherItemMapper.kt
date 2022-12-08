package com.example.picsangloginapp.domain.login

import com.example.picsangloginapp.core.mapper.Mapper

class WeatherItemMapper : Mapper<WeatherItem,WeatherDto>{

    override fun map(source: WeatherDto)= when{
        source.weatherInnerDto.isEmpty() -> WeatherItem.Error()
        source.weatherInnerDto[0].description.isEmpty()->WeatherItem.Error()
        else-> WeatherItem.Basic(
            description = source.weatherInnerDto[0].descripion,
            temp=source.main.temp.toInt(),
            feelsLike = source.feelsLike.toInt()
        )
    }
}