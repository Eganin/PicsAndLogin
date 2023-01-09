package com.example.picsangloginapp.domain.login

import com.example.picsangloginapp.core.exception.ExceptionHandler
import com.example.picsangloginapp.core.exception.ExceptionHandlerImpl
import com.best.login_impl.WeatherDto
import com.best.login_impl.WeatherInnerDto
import com.best.login_impl.WeatherMainInfoDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
internal class LoginInteractorImplTest {

    private lateinit var mapper: com.best.login_api.WeatherItemMapper
    private lateinit var exceptionHandler: ExceptionHandler
    private lateinit var repository: com.best.login_api.LoginRepository
    private lateinit var interactor: com.best.login_api.LoginInteractor

    @Before
    fun setup() {
        mapper = com.best.login_api.WeatherItemMapper()
        exceptionHandler = ExceptionHandlerImpl()
    }

    @Test
    fun test_positive_case() = runTest {
        repository = TestLoginRepository(getSuccess = true)
        interactor = com.best.login_api.LoginInteractorImpl(repository, mapper, exceptionHandler)
        val result = interactor.login()
        assertThat(result is WeatherItem.Basic).isTrue()
    }

    @Test
    fun test_negative_case() = runTest {
        repository = TestLoginRepository(getSuccess = false)
        interactor = com.best.login_api.LoginInteractorImpl(repository, mapper, exceptionHandler)
        val result = interactor.login()
        assertThat(result is WeatherItem.Error).isTrue()
    }

    private inner class TestLoginRepository(private val getSuccess: Boolean) :
        com.best.login_api.LoginRepository {
        override suspend fun getWeather(): com.best.login_impl.WeatherDto {
            return if (getSuccess)
                com.best.login_impl.WeatherDto(
                    listOf(com.best.login_impl.WeatherInnerDto("a")),
                    com.best.login_impl.WeatherMainInfoDto(1.3f, 2.2f)
                )
            else throw UnknownHostException()
        }
    }
}