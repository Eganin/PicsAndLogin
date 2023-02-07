package com.best.login_impl.di

import com.best.core.di.CoreComponent
import com.best.login_api.di.LoginComponent
import com.best.login_api.di.LoginScope
import dagger.Component

@Component(
    modules = [LoginModule::class, LoginNetworkModule::class],
    dependencies = [CoreComponent::class]
)
@LoginScope
interface LoginComponentImpl : LoginComponent {

    @Component.Builder
    interface Builder : LoginComponent.Builder
}