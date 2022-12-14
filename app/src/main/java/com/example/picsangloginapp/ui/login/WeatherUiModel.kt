package com.example.picsangloginapp.ui.login

import com.example.picsangloginapp.core.observer.ViewModelCommunication
import com.example.picsangloginapp.core.other.TextContainer

data class WeatherUiModel(
    private val description: String,
    private val isError: Boolean = false
) {

    fun map(communication: ViewModelCommunication<LoginState>) =
        communication.map(
            if (isError)
                LoginState.Error(value = this)
            else
                LoginState.Success(value = this)
        )

    fun show(textContainer: TextContainer) = textContainer.show(text = description)
}