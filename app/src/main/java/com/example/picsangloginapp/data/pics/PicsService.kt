package com.example.picsangloginapp.data.pics

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface PicsService {

    @GET("list")
    suspend fun getPics(
        @Query("page") page : Int,
        @Query("limit") limit : Int
    ): ResponseBody
}