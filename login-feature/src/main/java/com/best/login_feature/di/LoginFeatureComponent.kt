package com.best.login_feature.di

import com.best.core.di.CoreComponent
import com.best.core.di.viewmodel.VmFactoryWrapper
import com.best.login_api.di.LoginComponent
import dagger.Component

@Component(
    dependencies = [CoreComponent::class,LoginComponent::class],
    modules = [LoginFeatureModule::class]
)
@LoginFeatureScope
interface LoginFeatureComponent {

    fun inject(vmFactoryWrapper: VmFactoryWrapper)
}