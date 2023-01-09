package com.best.login_impl.di

import com.best.login_api.di.LoginComponent
import com.best.login_api.di.LoginComponentDependencies
import com.best.login_api.di.LoginComponentHolder

object LoginComponentImplHolder : LoginComponentHolder() {
    override fun createComponent(dependencies: LoginComponentDependencies): LoginComponent=
        DaggerLoginComponentImpl.builder()
            .coreComponent(dependencies.coreComponent)
            .build()
}