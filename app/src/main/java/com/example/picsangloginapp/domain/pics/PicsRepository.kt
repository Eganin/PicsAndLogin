package com.example.picsangloginapp.domain.pics

import com.example.picsangloginapp.data.pics.PicDto

interface PicsRepository {

    fun getCachedData() : List<PicDto>

    suspend fun getData() : List<PicDto>
}