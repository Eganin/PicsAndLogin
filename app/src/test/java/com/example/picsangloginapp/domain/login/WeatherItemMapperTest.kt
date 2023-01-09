package com.example.picsangloginapp.domain.login

import com.best.login_impl.WeatherDto
import com.best.login_impl.WeatherInnerDto
import com.best.login_impl.WeatherMainInfoDto
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class WeatherItemMapperTest {

    private val mapper = com.best.login_api.WeatherItemMapper()

    @Test
    fun test_empty_data() {
        val source = com.best.login_impl.WeatherDto(
            weatherInnerDto = emptyList(),
            main = com.best.login_impl.WeatherMainInfoDto(temp = 1f, feelsLike = 2f)
        )
        val result = mapper.map(source = source)
        val expected = WeatherItem.Error()
        assertThat(result is WeatherItem.Error).isTrue()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_empty_description() {
        val source = com.best.login_impl.WeatherDto(
            weatherInnerDto = listOf(com.best.login_impl.WeatherInnerDto(description = "")),
            main = com.best.login_impl.WeatherMainInfoDto(temp = 1f, feelsLike = 2f)
        )
        val result = mapper.map(source = source)
        val expected = WeatherItem.Error()
        assertThat(result is WeatherItem.Error).isTrue()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_basic_case() {
        val source = com.best.login_impl.WeatherDto(
            listOf(com.best.login_impl.WeatherInnerDto("some")),
            com.best.login_impl.WeatherMainInfoDto(1.5f, 2.3f)
        )
        val result = mapper.map(source)
        val expected = WeatherItem.Basic("some", 1, 2)
        assertThat(result is WeatherItem.Basic).isTrue()
        assertThat(result).isEqualTo(expected)
    }
}