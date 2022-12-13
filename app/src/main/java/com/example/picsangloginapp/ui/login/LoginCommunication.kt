package com.example.picsangloginapp.ui.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Observe {
    fun observe(owner: LifecycleOwner, observer: Observer<LoginState>)
}

interface LoginCommunication : Observe {

    fun map(source: LoginState)

    class Base(
        private val liveData: MutableLiveData<LoginState> = MutableLiveData()
    ) : LoginCommunication {

        override fun observe(owner: LifecycleOwner, observer: Observer<LoginState>) {
            liveData.observe(owner, observer)
        }

        override fun map(source: LoginState) {
            liveData.value = source
        }
    }
}