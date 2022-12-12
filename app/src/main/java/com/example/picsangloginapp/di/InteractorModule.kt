package com.example.picsangloginapp.di

import com.example.picsangloginapp.domain.login.LoginInteractor
import com.example.picsangloginapp.domain.login.LoginInteractorImpl
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
    abstract fun bindLoginInteractor(loginInteractorImpl: LoginInteractorImpl): LoginInteractor

    @Binds
    @Singleton
    abstract fun bindPicsInteractor(picsInteractorImpl: PicsInteractorImpl): PicsInteractor
}