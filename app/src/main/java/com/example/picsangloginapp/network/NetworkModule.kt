package com.example.picsangloginapp.network

import com.example.picsangloginapp.data.login.LoginService
import com.example.picsangloginapp.data.pics.PicsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

class NetworkModule @Inject constructor() {

    companion object {
        const val PICS_BASE_URL = "https://picsum.photos/v2/"
        const val LOGIN_BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    private val converterFactory = GsonConverterFactory.create()

    fun getPicsService(): PicsService = Retrofit.Builder()
        .baseUrl(PICS_BASE_URL)
        .addConverterFactory(converterFactory)
        .build()
        .create()

    fun getLoginService(): LoginService {
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
            .addConverterFactory(converterFactory)
            .client(loginClient)
            .build()
            .create()
    }
}