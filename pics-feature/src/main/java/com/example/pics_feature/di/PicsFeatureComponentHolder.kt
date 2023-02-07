package com.example.pics_feature.di

import com.best.core.di.BaseComponentHolderCore
import com.best.core.di.CoreComponent
import com.example.pics_api.di.PicComponent


data class PicsFeatureComponentDependencies(
    val coreComponent: CoreComponent,
    val picsComponent: PicComponent
)

object PicsFeatureComponentHolder :
    BaseComponentHolderCore.Dependent<PicsFeatureComponent, PicsFeatureComponentDependencies>() {
    override fun createComponent(dependencies: PicsFeatureComponentDependencies): PicsFeatureComponent =
        DaggerPicsFeatureComponent.builder()
            .coreComponent(dependencies.coreComponent)
            .picComponent(dependencies.picsComponent)
            .build()
}