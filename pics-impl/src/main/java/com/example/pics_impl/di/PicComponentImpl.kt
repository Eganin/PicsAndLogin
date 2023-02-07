package com.example.pics_impl.di

import com.best.core.di.CoreComponent
import com.example.pics_api.di.PicComponent
import com.example.pics_api.di.PicScope
import dagger.Component

@Component(
    modules = [PicsModule::class, PicsNetworkModule::class],
    dependencies = [CoreComponent::class]
)
@PicScope
interface PicComponentImpl : PicComponent {

    @Component.Builder
    interface Builder : PicComponent.Builder
}