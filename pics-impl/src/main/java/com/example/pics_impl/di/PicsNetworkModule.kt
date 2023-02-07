package com.example.pics_impl.di

import com.example.pics_api.PicsService
import com.example.pics_api.di.PicScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
internal class PicsNetworkModule {

    @Provides
    @PicScope
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @PicScope
    internal fun providePicsService(converterFactory: GsonConverterFactory): PicsService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
            .create()

    companion object {
        private const val BASE_URL = "https://picsum.photos/v2/"
    }
}