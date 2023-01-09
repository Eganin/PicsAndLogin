package com.best.core.di

import android.app.Application
import android.content.Context
import com.best.core.dispatchers.DispatchersList
import com.best.core.exception.ExceptionHandler
import com.best.core.exception.ExceptionHandlerImpl
import com.best.core.resourcemanager.ResourceManager
import com.best.core.resourcemanager.ResourceManagerImpl
import dagger.Module
import dagger.Provides

@Module
class CoreModule {

    @CoreScope
    @Provides
    internal fun provideContext(application: Application): Context = application

    @CoreScope
    @Provides
    internal fun provideDispatchersList(): DispatchersList = DispatchersList.Base()

    @CoreScope
    @Provides
    internal fun provideExceptionHandler(): ExceptionHandler = ExceptionHandlerImpl()

    @CoreScope
    @Provides
    internal fun provideResourceManager(context: Context): ResourceManager =
        ResourceManagerImpl(context = context)
}