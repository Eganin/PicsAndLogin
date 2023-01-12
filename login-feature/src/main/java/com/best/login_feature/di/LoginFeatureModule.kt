package com.best.login_feature.di

import androidx.lifecycle.ViewModel
import com.best.core.di.viewmodel.ViewModelKey
import com.best.core.observer.ViewModelCommunication
import com.best.core.resourcemanager.ResourceManager
import com.best.core.validation.UiValidator
import com.best.core.validation.UiValidatorChain
import com.best.login_api.WeatherUiMapper
import com.best.login_feature.*
import com.best.login_feature.validators.EmptinessValidator
import com.best.login_feature.validators.MinLengthValidator
import com.best.login_feature.validators.PasswordValidator
import com.example.picsangloginapp.di.EmailValidation
import com.example.picsangloginapp.di.PasswordValidation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [LoginFeatureModule.BindsModule::class])
class LoginFeatureModule {

    @LoginFeatureScope
    @Provides
    fun provideLoginCommunication(): ViewModelCommunication<LoginState> =
        ViewModelCommunication.Base()

    @LoginFeatureScope
    @Provides
    fun provideWeatherUiMapper(resourceManager: ResourceManager): WeatherUiMapper<WeatherUiModel> =
        WeatherUiMapperImpl(resourceManager = resourceManager)

    @LoginFeatureScope
    @Provides
    fun provideEmptyValidator(resourceManager: ResourceManager): UiValidator {
        return EmptinessValidator(resourceManager.getString(R.string.empty_string_error_message))
    }

    @LoginFeatureScope
    @Provides
    @PasswordValidation
    fun provideValidatePassword(
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
                nextChain = PasswordValidator(
                    errorMessage = resourceManager.getString(
                        R.string.invalid_password_error_message
                    )
                )
            )
        )
    }

    @LoginFeatureScope
    @Provides
    @EmailValidation
    fun provideValidateEmail(
        resourceManager: ResourceManager,
        emptyValidator: UiValidator
    ): UiValidator {
        return UiValidatorChain(
            currentChain = emptyValidator,
            nextChain = com.best.login_feature.validators.EmailValidator(
                errorMessage = resourceManager.getString(
                    R.string.invalid_email_error_message
                )
            )
        )
    }

    @Module
    interface BindsModule {
        @Binds
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
    }
}