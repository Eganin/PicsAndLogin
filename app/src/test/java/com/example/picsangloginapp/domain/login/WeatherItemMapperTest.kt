package com.example.picsangloginapp.domain.login

import com.best.login_api.WeatherItem
import com.best.login_impl.WeatherDto
import com.best.login_impl.WeatherInnerDto
import com.best.login_impl.WeatherItemMapper
import com.best.login_impl.WeatherMainInfoDto
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class WeatherItemMapperTest {

    private val mapper = WeatherItemMapper()

    @Test
    fun test_empty_data() {
        val source = WeatherDto(
            weatherInnerDto = emptyList(),
            main = WeatherMainInfoDto(temp = 1f, feelsLike = 2f)
        )
        val result = mapper.map(source = source)
        val expected = WeatherItem.Error()
        assertThat(result is WeatherItem.Error).isTrue()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_empty_description() {
        val source = WeatherDto(
            weatherInnerDto = listOf(WeatherInnerDto(description = "")),
            main = WeatherMainInfoDto(temp = 1f, feelsLike = 2f)
        )
        val result = mapper.map(source = source)
        val expected = WeatherItem.Error()
        assertThat(result is WeatherItem.Error).isTrue()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_basic_case() {
        val source = WeatherDto(
            listOf(WeatherInnerDto("some")),
            WeatherMainInfoDto(1.5f, 2.3f)
        )
        val result = mapper.map(source)
        val expected = WeatherItem.Basic("some", 1, 2)
        assertThat(result is WeatherItem.Basic).isTrue()
        assertThat(result).isEqualTo(expected)
    }
}