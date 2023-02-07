package com.example.pics_impl

import com.best.core.mapper.Mapper
import com.best.core.other.stringSuspending
import com.example.pics_api.PicItem
import com.example.pics_api.PicsRepository
import com.example.pics_api.PicsService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class PicsRepositoryImpl @Inject constructor(
    private val service: PicsService,
    private val mapper: Mapper<List<PicItem>, List<PicDto>>
) : PicsRepository {

    private val gson = Gson()
    private val type = object : TypeToken<List<PicDto>>() {}.type

    private val dataList = mutableListOf<PicDto>()
    private var page = 0

    override fun getCachedData() = mapper.map(source = dataList)

    override suspend fun getData(): List<PicItem> {
        val data = service.getPics(page = page, limit = 30)
        val list = gson.fromJson<List<PicDto>>(data.stringSuspending(), type)
        if (list.isNotEmpty()) {
            dataList.addAll(list)
            page++
        }
        return mapper.map(source = dataList)
    }
}