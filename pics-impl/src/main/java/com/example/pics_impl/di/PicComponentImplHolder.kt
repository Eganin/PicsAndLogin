package com.example.pics_impl.di

import com.example.pics_api.di.PicComponent
import com.example.pics_api.di.PicComponentDependencies
import com.example.pics_api.di.PicComponentHolder

object PicComponentImplHolder : PicComponentHolder() {
    override fun createComponent(dependencies: PicComponentDependencies): PicComponent =
        DaggerPicComponentImpl.builder()
            .coreComponent(dependencies.coreComponent)
            .build()
}