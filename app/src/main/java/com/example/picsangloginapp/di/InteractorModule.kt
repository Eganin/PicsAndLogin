package com.example.picsangloginapp.di

import com.best.login_api.LoginInteractor
import com.best.login_api.LoginInteractorImpl
import com.example.picsangloginapp.domain.pics.PicsInteractor
import com.example.picsangloginapp.domain.pics.PicsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    @Singleton
    abstract fun bindLoginInteractor(loginInteractorImpl: com.best.login_api.LoginInteractorImpl): com.best.login_api.LoginInteractor

    @Binds
    @Singleton
    abstract fun bindPicsInteractor(picsInteractorImpl: PicsInteractorImpl): PicsInteractor
}