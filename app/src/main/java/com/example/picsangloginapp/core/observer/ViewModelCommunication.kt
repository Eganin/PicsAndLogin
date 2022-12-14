package com.example.picsangloginapp.core.observer

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface ViewModelCommunication<T> : Observe<T> {
    fun map(source: T)

    class Base<T>(
        private val liveData: MutableLiveData<T> = MutableLiveData()
    ) : ViewModelCommunication<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }

        override fun map(source: T) {
            liveData.value = source!!
        }
    }
}
