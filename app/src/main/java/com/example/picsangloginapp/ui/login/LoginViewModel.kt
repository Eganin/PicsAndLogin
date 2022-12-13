package com.example.picsangloginapp.ui.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsangloginapp.core.dispatchers.DispatchersList
import com.example.picsangloginapp.core.validation.UiValidator
import com.example.picsangloginapp.di.EmailValidation
import com.example.picsangloginapp.di.PasswordValidation
import com.example.picsangloginapp.domain.login.LoginInteractor
import com.example.picsangloginapp.domain.login.WeatherUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val communication: LoginCommunication,
    private val interactor: LoginInteractor,
    private val mapper: WeatherUiMapper<WeatherUiModel>,
    @EmailValidation private val validateEmail: UiValidator,
    @PasswordValidation private val validatePassword: UiValidator,
    private val dispatchers: DispatchersList
) : ViewModel(), Observe {

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