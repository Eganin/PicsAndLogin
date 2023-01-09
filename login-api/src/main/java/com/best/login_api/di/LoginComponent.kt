package com.best.login_api.di

import com.best.core.di.CoreComponent

interface LoginComponent {

    interface Builder{

        fun coreComponent(component: CoreComponent): Builder
        fun build(): LoginComponent
    }

    companion object
}