package com.example.pics_api

interface PicsRepository {

    fun getCachedData(): List<PicItem>

    suspend fun getData(): List<PicItem>
}