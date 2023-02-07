package com.example.pics_feature.di

import com.best.core.di.CoreComponent
import com.best.core.di.viewmodel.VmFactoryWrapper
import com.example.pics_api.di.PicComponent
import dagger.Component

@Component(
    dependencies = [CoreComponent::class,PicComponent::class],
    modules = [PicsFeatureModule::class]
)
@PicsFeatureScope
interface PicsFeatureComponent {

    fun inject(vmFactoryWrapper: VmFactoryWrapper)
}