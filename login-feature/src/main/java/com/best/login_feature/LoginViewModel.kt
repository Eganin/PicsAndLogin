package com.best.login_feature

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.core.dispatchers.DispatchersList
import com.best.core.observer.Observe
import com.best.core.observer.ViewModelCommunication
import com.best.core.validation.UiValidator
import com.best.login_api.LoginInteractor
import com.best.login_api.models.WeatherUiMapper
import com.best.login_feature.models.LoginState
import com.best.login_feature.models.WeatherUiModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LoginViewModel @Inject constructor(
    private val communication: ViewModelCommunication<LoginState>,
    private val interactor: LoginInteractor,
    private val mapper: WeatherUiMapper<WeatherUiModel>,
    @EmailValidation private val validateEmail: UiValidator,
    @PasswordValidation private val validatePassword: UiValidator,
    private val dispatchers: DispatchersList
) : ViewModel(), Observe<LoginState> {

    override fun observe(owner: LifecycleOwner, observer: Observer<LoginState>) {
        communication.observe(owner = owner, observer = observer)
    }

    fun login(email: String, password: String) {
        if (validateEmail.isValid(text = email) && validatePassword.isValid(text = password)) {
            communication.map(source = LoginState.Progress)
            viewModelScope.launch(context = dispatchers.io()) {
                val result = interactor.login()
                withContext(context = dispatchers.ui()) {
                    result.map(mapper = mapper).map(communication = communication)
                }
            }
        } else if (!validateEmail.isValid(text = email) && !validatePassword.isValid(text = password))
            communication.map(
                source = LoginState.TwoErrors(
                    loginError = validateEmail.errorMessage(),
                    passwordError = validatePassword.errorMessage()
                )
            )
        else if (validateEmail.isValid(text = email) && !validatePassword.isValid(text = password))
            communication.map(
                source = LoginState.PasswordError(validatePassword.errorMessage())
            )
        else communication.map(source = LoginState.LoginError(validateEmail.errorMessage()))
    }
}