package com.example.picsangloginapp.ui.login

import com.example.picsangloginapp.core.other.TextContainer

data class WeatherUiModel(
    private val description : String,
    private val isError : Boolean=false
){

    fun map(communication: LoginCommunication)=
        communication.map(
            if(isError)
                LoginState.Error(value=this)
            else
                LoginState.Success(value =this)
        )

    fun show(textContainer: TextContainer) = textContainer.show(text = description)
}