package com.example.picsangloginapp.di

import com.example.picsangloginapp.data.login.LoginRepositoryImpl
import com.example.picsangloginapp.data.pics.PicsRepositoryImpl
import com.example.picsangloginapp.domain.login.LoginRepository
import com.example.picsangloginapp.domain.pics.PicsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun bindPicsRepository(picsRepositoryImpl: PicsRepositoryImpl): PicsRepository
}