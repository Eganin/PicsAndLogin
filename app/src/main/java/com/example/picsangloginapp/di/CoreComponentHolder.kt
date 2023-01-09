package com.example.picsangloginapp.di

import android.app.Application
import com.best.core.di.BaseComponentHolderCore
import com.best.core.di.CoreComponent
import com.best.core.di.DaggerCoreComponent

data class CoreComponentDependencies(
    val application: Application
)

object CoreComponentHolder: BaseComponentHolderCore.Dependent<CoreComponent,CoreComponentDependencies>() {
    override fun createComponent(dependencies: CoreComponentDependencies): CoreComponent=
        DaggerCoreComponent.builder()
            .application(dependencies.application)
            .build()
}