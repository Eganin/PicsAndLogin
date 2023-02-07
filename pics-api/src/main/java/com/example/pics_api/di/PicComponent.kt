package com.example.pics_api.di

import com.best.core.di.CoreComponent
import com.example.pics_api.PicsInteractor

interface PicComponent {

    interface Builder {

        fun coreComponent(component: CoreComponent): Builder

        fun build(): PicComponent
    }

    fun providePicsInteractor(): PicsInteractor

    companion object
}