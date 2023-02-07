package com.example.picsangloginapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.best.core.dispatchers.DispatchersList
import com.best.core.observer.ViewModelCommunication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

internal open class BaseUiTest {

    class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    ) : DispatchersList {

        override fun io() = dispatcher
        override fun ui() = dispatcher
    }

    interface FakeCommunication<T> : ViewModelCommunication<T> {

        fun state(): T

        class Base<T> : FakeCommunication<T> {

            private var state: T? = null

            override fun state() = state!!

            override fun map(source: T) {
                state = source
            }

            override fun observe(owner: LifecycleOwner, observer: Observer<T>) = Unit
        }
    }
}