package com.example.picsangloginapp.di

import android.content.Context
import com.example.picsangloginapp.R
import com.example.picsangloginapp.core.dispatchers.DispatchersList
import com.example.picsangloginapp.core.exception.ExceptionHandler
import com.example.picsangloginapp.core.exception.ExceptionHandlerImpl
import com.example.picsangloginapp.core.mapper.Mapper
import com.example.picsangloginapp.core.network.NetworkModule
import com.example.picsangloginapp.core.observer.ViewModelCommunication
import com.example.picsangloginapp.core.resourcemanager.ResourceManager
import com.example.picsangloginapp.core.resourcemanager.ResourceManagerImpl
import com.example.picsangloginapp.core.validation.UiValidator
import com.example.picsangloginapp.core.validation.UiValidatorChain
import com.example.picsangloginapp.data.login.LoginService
import com.example.picsangloginapp.data.login.WeatherDto
import com.example.picsangloginapp.data.pics.PicDto
import com.example.picsangloginapp.data.pics.PicsService
import com.example.picsangloginapp.domain.login.*
import com.example.picsangloginapp.domain.login.validators.EmailValidator
import com.example.picsangloginapp.domain.login.validators.EmptinessValidator
import com.example.picsangloginapp.domain.login.validators.MinLengthValidator
import com.example.picsangloginapp.domain.login.validators.PasswordValidator
import com.example.picsangloginapp.domain.pics.*
import com.example.picsangloginapp.ui.login.LoginState
import com.example.picsangloginapp.ui.login.WeatherUiMapperImpl
import com.example.picsangloginapp.ui.login.WeatherUiModel
import com.example.picsangloginapp.ui.pics.PicUiMapper
import com.example.picsangloginapp.ui.pics.PicsUiMapper
import com.example.picsangloginapp.ui.pics.adapter.PicUiModel
import com.example.picsangloginapp.ui.pics.adapter.PicUiModelType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule : InstanceProvider {

    @Singleton
    @Provides
    override fun provideNetworkModule() = NetworkModule()

    @Singleton
    @Provides
    override fun provideDispatchers(): DispatchersList {
        return DispatchersList.Base()
    }

    @Singleton
    @Provides
    override fun provideLoginService(networkModule: NetworkModule): LoginService =
        networkModule.getLoginService()

    @Singleton
    @Provides
    override fun provideEmptyValidator(resourceManager: ResourceManager): UiValidator {
        return EmptinessValidator(resourceManager.getString(R.string.empty_string_error_message))
    }

    @Singleton
    @Provides
    @EmailValidation
    override fun provideValidateEmail(
        resourceManager: ResourceManager,
        emptyValidator: UiValidator
    ): UiValidator {
        return UiValidatorChain(
            currentChain = emptyValidator,
            nextChain = EmailValidator(errorMessage = resourceManager.getString(R.string.invalid_email_error_message))
        )
    }

    @Singleton
    @Provides
    @PasswordValidation
    override fun provideValidatePassword(
        resourceManager: ResourceManager,
        emptyValidator: UiValidator
    ): UiValidator {
        val max = 6
        return UiValidatorChain(
            currentChain = emptyValidator,
            nextChain = UiValidatorChain(
                currentChain = MinLengthValidator(
                    errorMessage = resourceManager.getString(
                        R.string.invalid_min_length_error_message,
                        max
                    ), minLength = max
                ),
                nextChain = PasswordValidator(errorMessage = resourceManager.getString(R.string.invalid_password_error_message))
            )
        )
    }

    @Singleton
    @Provides
    override fun provideLoginCommunication(): ViewModelCommunication<LoginState> {
        return ViewModelCommunication.Base()
    }

    @Singleton
    @Provides
    override fun providePicsService(networkModule: NetworkModule): PicsService =
        networkModule.getPicsService()

    @Singleton
    @Provides
    override fun provideExceptionHandler(): ExceptionHandler {
        return ExceptionHandlerImpl()
    }

    @Singleton
    @Provides
    override fun provideResourceManager(@ApplicationContext context: Context): ResourceManager {
        return ResourceManagerImpl(context = context)
    }

    @Singleton
    @Provides
    override fun provideWeatherItemMapper(): Mapper<WeatherItem, WeatherDto> {
        return WeatherItemMapper()
    }

    @Singleton
    @Provides
    override fun provideWeatherUiMapper(resourceManager: ResourceManager): WeatherUiMapper<WeatherUiModel> {
        return WeatherUiMapperImpl(resourceManager = resourceManager)
    }

    @Singleton
    @Provides
    override fun providePicItemMapper(): Mapper<List<PicItem>, List<PicDto>> {
        return PicItemMapper()
    }

    @Singleton
    @Provides
    override fun providePicsUiMapper(resourceManager: ResourceManager): Mapper<List<PicUiModel>, List<PicItem>> {
        return PicsUiMapper(resourceManager = resourceManager)
    }

    @Singleton
    @Provides
    override fun providePicsCommunication(): ViewModelCommunication<List<PicUiModel>> {
        return ViewModelCommunication.Base()
    }
}