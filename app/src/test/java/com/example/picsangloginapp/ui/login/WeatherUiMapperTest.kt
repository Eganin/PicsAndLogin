package com.example.picsangloginapp.ui.login

import com.example.picsangloginapp.core.exception.ExceptionType
import com.example.picsangloginapp.core.resourcemanager.TestResourceManager
import com.example.picsangloginapp.domain.login.WeatherItem
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class WeatherUiMapperTest{

    private val mapper = WeatherUiMapperImpl(resourceManager = TestResourceManager())

    @Test
    fun test_basic(){
        val item = WeatherItem.Basic(description = "desc", temp = 1, feelsLike = 2)
        val result = item.map(mapper = mapper)
        val expected = WeatherUiModel(description = "stub with args")
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_network_error(){
        val item = WeatherItem.Error(exceptionType = ExceptionType.NETWORK_UNAVAILABLE)
        val result = item.map(mapper=mapper)
        val expected = WeatherUiModel(description = "network is not available!",isError = true)
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_generic_error(){
        val item = WeatherItem.Error(exceptionType = ExceptionType.GENERIC)
        val result = item.map(mapper=mapper)
        val expected = WeatherUiModel(description = "just generic error",isError = true)
        assertThat(result).isEqualTo(expected)
    }
}