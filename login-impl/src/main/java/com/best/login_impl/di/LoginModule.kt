package com.best.login_impl.di

import com.best.core.exception.ExceptionHandler
import com.best.core.mapper.Mapper
import com.best.login_api.LoginInteractor
import com.best.login_api.LoginRepository
import com.best.login_api.WeatherItem
import com.best.login_api.di.LoginScope
import com.best.login_impl.*
import dagger.Module
import dagger.Provides

@Module
internal class LoginModule {

    @LoginScope
    @Provides
    internal fun provideLoginInteractor(
        repository: LoginRepository,
        exceptionHandler: ExceptionHandler
    ): LoginInteractor =
        LoginInteractorImpl(
            repository = repository,
            exceptionHandler = exceptionHandler
        )

    @LoginScope
    @Provides
    internal fun provideWeatherItemMapper(): Mapper<WeatherItem, WeatherDto> = WeatherItemMapper()

    @LoginScope
    @Provides
    internal fun provideLoginRepository(
        service: LoginService,
        mapper: Mapper<WeatherItem, WeatherDto>
    ): LoginRepository = LoginRepositoryImpl(service = service, mapper = mapper)

    companion object {
        const val LOGIN_BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}