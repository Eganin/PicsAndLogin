package com.example.pics_api

import com.example.pics_api.models.PicItem

interface PicsRepository {

    fun getCachedData(): List<PicItem>

    suspend fun getData(): List<PicItem>
}