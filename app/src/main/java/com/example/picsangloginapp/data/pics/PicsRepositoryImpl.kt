package com.example.picsangloginapp.data.pics

import com.best.core.other.stringSuspending
import com.example.picsangloginapp.domain.pics.PicsRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class PicsRepositoryImpl @Inject constructor(private val service: PicsService) : PicsRepository {

    private val gson = Gson()
    private val type = object : TypeToken<List<PicDto>>() {}.type

    private val dataList = mutableListOf<PicDto>()
    private var page = 0

    override fun getCachedData() = dataList

    override suspend fun getData(): List<PicDto> {
        val data = service.getPics(page = page, limit = 30)
        val list = gson.fromJson<List<PicDto>>(data.stringSuspending(), type)
        if (list.isNotEmpty()) {
            dataList.addAll(list)
            page++
        }
        return dataList
    }
}