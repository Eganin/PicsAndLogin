package com.example.picsangloginapp.di

import android.content.Context
import com.best.core.dispatchers.DispatchersList
import com.best.core.exception.ExceptionHandler
import com.best.core.mapper.Mapper
import com.best.core.observer.ViewModelCommunication
import com.best.core.resourcemanager.ResourceManager
import com.best.core.validation.UiValidator
import com.best.login_impl.LoginService
import com.best.login_impl.WeatherDto
import com.example.picsangloginapp.data.pics.PicDto
import com.example.picsangloginapp.data.pics.PicsService
import com.example.picsangloginapp.domain.login.WeatherItem
import com.example.picsangloginapp.domain.login.WeatherUiMapper
import com.example.picsangloginapp.domain.pics.PicItem
import com.example.picsangloginapp.network.NetworkModule
import com.best.login_feature.LoginState
import com.best.login_feature.WeatherUiModel
import com.example.picsangloginapp.ui.pics.adapter.PicUiModel

interface InstanceProvider : LoginInstancesProvider, PicsInstancesProvider {

    fun provideExceptionHandler(): ExceptionHandler
    fun provideResourceManager(context: Context): ResourceManager
    fun provideNetworkModule(): NetworkModule
    fun provideDispatchers(): DispatchersList
}

interface LoginInstancesProvider : LoginUiInstanceProvider {

    fun provideWeatherItemMapper(): Mapper<WeatherItem, com.best.login_impl.WeatherDto>
    fun provideLoginService(networkModule: NetworkModule): com.best.login_impl.LoginService
}

interface LoginUiInstanceProvider {

    fun provideWeatherUiMapper(resourceManager: ResourceManager): WeatherUiMapper<com.best.login_feature.WeatherUiModel>
    fun provideEmptyValidator(resourceManager: ResourceManager): UiValidator
    fun provideValidateEmail(
        resourceManager: ResourceManager,
        emptyValidator: UiValidator
    ): UiValidator

    fun provideValidatePassword(
        resourceManager: ResourceManager,
        emptyValidator: UiValidator
    ): UiValidator

    fun provideLoginCommunication(): ViewModelCommunication<com.best.login_feature.LoginState>
}

interface PicsInstancesProvider : PicsUiInstanceProvider {

    fun providePicItemMapper(): Mapper<List<PicItem>, List<PicDto>>
    fun providePicsService(networkModule: NetworkModule): PicsService
}

interface PicsUiInstanceProvider {

    fun providePicsUiMapper(resourceManager: ResourceManager): Mapper<List<PicUiModel>, List<PicItem>>
    fun providePicsCommunication(): ViewModelCommunication<List<PicUiModel>>
}