package com.best.core.di

import android.app.Application
import android.content.Context
import com.best.core.dispatchers.DispatchersList
import com.best.core.exception.ExceptionHandler
import com.best.core.exception.ExceptionHandlerImpl
import com.best.core.resourcemanager.ResourceManager
import com.best.core.resourcemanager.ResourceManagerImpl
import dagger.BindsInstance
import dagger.Component
import dagger.Provides

@Component(
    modules = [CoreModule::class]
)
@CoreScope
interface CoreComponent {

    fun provideContext(): Context

    fun provideDispatchersList(): DispatchersList

    fun provideExceptionHandler(): ExceptionHandler

    fun provideResourceManager(): ResourceManager

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }
}