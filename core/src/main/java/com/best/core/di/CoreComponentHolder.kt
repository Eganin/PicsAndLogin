package com.best.core.di

import android.app.Application

data class CoreComponentDependencies(
    val application: Application
)

object CoreComponentHolder: BaseComponentHolderCore.Dependent<CoreComponent, CoreComponentDependencies>() {
    override fun createComponent(dependencies: CoreComponentDependencies): CoreComponent=
        DaggerCoreComponent.builder()
            .application(dependencies.application)
            .build()
}