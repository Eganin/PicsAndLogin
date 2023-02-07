package com.best.login_impl.di

import com.best.login_api.LoginService
import com.best.login_api.di.LoginScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
internal class LoginNetworkModule {

    @LoginScope
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @LoginScope
    @Provides
    internal fun provideLoginService(gsonConverterFactory: GsonConverterFactory): LoginService {
        val loginClient = OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val originalUrl = original.url
                val url = originalUrl.newBuilder()
                    .addQueryParameter("appid", "c35880b49ff95391b3a6d0edd0c722eb")
                    .build()

                val request = original.newBuilder()
                    .url(url)
                    .build()

                it.proceed(request = request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(LOGIN_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(loginClient)
            .build()
            .create()
    }

    companion object {
        private const val LOGIN_BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}