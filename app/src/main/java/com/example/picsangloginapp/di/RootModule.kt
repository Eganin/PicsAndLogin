package com.example.picsangloginapp.di

import com.example.picsangloginapp.data.RootInteractorImpl
import com.example.picsangloginapp.domain.RootInteractor
import dagger.Module
import dagger.Provides

@Module
class RootModule {

    @Provides
    @RootScope
    fun provideRootInteractor(): RootInteractor = RootInteractorImpl()
}