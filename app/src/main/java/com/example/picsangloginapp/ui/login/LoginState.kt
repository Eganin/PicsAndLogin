package com.example.picsangloginapp.ui.login

import com.example.picsangloginapp.other.MySnackBar
import com.example.picsangloginapp.databinding.FragmentLayoutBinding
import com.google.android.material.snackbar.Snackbar

sealed class LoginState {
    abstract fun handle(binding: FragmentLayoutBinding)

    object Progress : LoginState() {

        override fun handle(binding: FragmentLayoutBinding) = binding.progressBar.show(show = false)
    }

    data class TwoErrors(
        private val loginError: String,
        private val passwordError: String
    ) : LoginState() {

        override fun handle(binding: FragmentLayoutBinding) = with(binding) {
            emailAddressTextInputLayout.show(show = true, message = loginError)
            passwordTextInputLayout.show(show = true, message = passwordError)
        }
    }

    data class LoginError(private val value : String) : LoginState(){

        override fun handle(binding: FragmentLayoutBinding)= with(binding) {
            emailAddressTextInputLayout.show(show = true, message = value)
        }
    }

    data class PasswordError(private val value : String): LoginState(){

        override fun handle(binding: FragmentLayoutBinding) = with(binding){
            passwordTextInputLayout.show(show=true, message = value)
        }
    }

    data class Success(private val value : WeatherUiModel): LoginState(){

        override fun handle(binding: FragmentLayoutBinding)= with(binding) {
            progressBar.show(show = false)
            value.show(MySnackBar(snackBar = Snackbar.make(binding.progressBar,"",Snackbar.LENGTH_SHORT)))
        }
    }

    data class Error(private val value : WeatherUiModel): LoginState(){

        override fun handle(binding: FragmentLayoutBinding) = with(binding) {
            progressBar.show(show = false)
            value.show(MySnackBar(snackBar = Snackbar.make(binding.progressBar,"",Snackbar.LENGTH_SHORT)))
        }
    }
}
