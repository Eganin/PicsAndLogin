package com.best.login_feature.di

import com.best.core.di.BaseComponentHolderCore
import com.best.core.di.CoreComponent
import com.best.login_api.di.LoginComponent

data class LoginFeatureComponentDependencies(
    val coreComponent: CoreComponent,
    val loginComponent: LoginComponent
)

object LoginFeatureComponentHolder :
    BaseComponentHolderCore.Dependent<LoginFeatureComponent, LoginFeatureComponentDependencies>() {
    override fun createComponent(dependencies: LoginFeatureComponentDependencies): LoginFeatureComponent =
        DaggerLoginFeatureComponent.builder()
            .coreComponent(dependencies.coreComponent)
            .loginComponent(dependencies.loginComponent)
            .build()
}