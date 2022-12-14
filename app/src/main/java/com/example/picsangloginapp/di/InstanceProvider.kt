package com.example.picsangloginapp.di

import android.content.Context
import com.example.picsangloginapp.core.dispatchers.DispatchersList
import com.example.picsangloginapp.core.exception.ExceptionHandler
import com.example.picsangloginapp.core.mapper.Mapper
import com.example.picsangloginapp.core.network.NetworkModule
import com.example.picsangloginapp.core.observer.ViewModelCommunication
import com.example.picsangloginapp.core.resourcemanager.ResourceManager
import com.example.picsangloginapp.core.validation.UiValidator
import com.example.picsangloginapp.data.login.LoginService
import com.example.picsangloginapp.data.login.WeatherDto
import com.example.picsangloginapp.data.pics.PicDto
import com.example.picsangloginapp.data.pics.PicsService
import com.example.picsangloginapp.domain.login.WeatherItem
import com.example.picsangloginapp.domain.login.WeatherUiMapper
import com.example.picsangloginapp.domain.pics.PicItem
import com.example.picsangloginapp.ui.login.LoginState
import com.example.picsangloginapp.ui.login.WeatherUiModel
import com.example.picsangloginapp.ui.pics.adapter.PicUiModel

interface InstanceProvider : LoginInstancesProvider, PicsInstancesProvider {

    fun provideExceptionHandler(): ExceptionHandler
    fun provideResourceManager(context: Context): ResourceManager
    fun provideNetworkModule(): NetworkModule
    fun provideDispatchers(): DispatchersList
}

interface LoginInstancesProvider : LoginUiInstanceProvider {

    fun provideWeatherItemMapper(): Mapper<WeatherItem, WeatherDto>
    fun provideLoginService(networkModule: NetworkModule): LoginService
}

interface LoginUiInstanceProvider {

    fun provideWeatherUiMapper(resourceManager: ResourceManager): WeatherUiMapper<WeatherUiModel>
    fun provideEmptyValidator(resourceManager: ResourceManager): UiValidator
    fun provideValidateEmail(
        resourceManager: ResourceManager,
        emptyValidator: UiValidator
    ): UiValidator

    fun provideValidatePassword(
        resourceManager: ResourceManager,
        emptyValidator: UiValidator
    ): UiValidator

    fun provideLoginCommunication(): ViewModelCommunication<LoginState>
}

interface PicsInstancesProvider : PicsUiInstanceProvider {

    fun providePicItemMapper(): Mapper<List<PicItem>, List<PicDto>>
    fun providePicsService(networkModule: NetworkModule): PicsService
}

interface PicsUiInstanceProvider {

    fun providePicsUiMapper(resourceManager: ResourceManager): Mapper<List<PicUiModel>, List<PicItem>>
    fun providePicsCommunication(): ViewModelCommunication<List<PicUiModel>>
}