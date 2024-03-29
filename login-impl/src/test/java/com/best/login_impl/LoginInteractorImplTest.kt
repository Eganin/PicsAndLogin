package com.best.login_impl

import com.best.core.exception.ExceptionHandler
import com.best.core.exception.ExceptionHandlerImpl
import com.best.login_api.*
import com.best.login_api.models.WeatherDto
import com.best.login_api.models.WeatherInnerDto
import com.best.login_api.models.WeatherItem
import com.best.login_api.models.WeatherMainInfoDto
import com.best.login_impl.*
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
internal class LoginInteractorImplTest {

    private lateinit var mapper: WeatherItemMapper
    private lateinit var exceptionHandler: ExceptionHandler
    private lateinit var repository: LoginRepository
    private lateinit var interactor: LoginInteractor

    @Before
    fun setup() {
        mapper = WeatherItemMapper()
        exceptionHandler = ExceptionHandlerImpl()
    }

    @Test
    fun test_positive_case() = runTest {
        repository = TestLoginRepository(getSuccess = true)
        interactor = LoginInteractorImpl(repository, exceptionHandler)
        val result = interactor.login()
        assertThat(result is WeatherItem.Basic).isTrue()
    }

    @Test
    fun test_negative_case() = runTest {
        repository = TestLoginRepository(getSuccess = false)
        interactor = LoginInteractorImpl(repository, exceptionHandler)
        val result = interactor.login()
        assertThat(result is WeatherItem.Error).isTrue()
    }

    private inner class TestLoginRepository(private val getSuccess: Boolean) : LoginRepository {
        override suspend fun getWeather(): WeatherItem {
            return if (getSuccess)
                mapper.map(
                    source = WeatherDto(
                        listOf(WeatherInnerDto("a")),
                        WeatherMainInfoDto(1.3f, 2.2f)
                    )
                )
            else throw UnknownHostException()
        }
    }
}