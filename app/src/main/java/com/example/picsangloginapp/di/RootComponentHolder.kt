package com.example.picsangloginapp.di

import com.best.core.di.BaseComponentHolderCore
import com.best.core.di.CoreComponent

data class RootComponentDependencies(
    val coreComponent: CoreComponent
)

object RootComponentHolder: BaseComponentHolderCore.Dependent<RootComponent,RootComponentDependencies>(){

    override fun createComponent(dependencies: RootComponentDependencies): RootComponent=
        DaggerRootComponent.builder()
            .coreComponent(dependencies.coreComponent)
            .build()
}