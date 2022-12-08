package com.example.picsangloginapp.data.login

import com.example.picsangloginapp.domain.login.LoginRepository
import java.net.UnknownHostException

class TestLoginRepositoryImpl : LoginRepository {

    private var count = 0

    override suspend fun getWeather(): WeatherDto {
        count++
        if (count % 2 ==0) throw UnknownHostException()
        return WeatherDto(
            weatherInnerDto = listOf(WeatherInnerDto(description = "test")),
            main = WeatherMainInfoDto(temp = 5.6f, feelsLike = 7f)
        )
    }
}