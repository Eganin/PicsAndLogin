package com.best.login_api.di

import com.best.core.di.CoreComponent
import com.best.login_api.LoginInteractor

interface LoginComponent {

    interface Builder{

        fun coreComponent(component: CoreComponent): Builder
        fun build(): LoginComponent
    }

    fun provideLoginInteractor(): LoginInteractor

    companion object
}