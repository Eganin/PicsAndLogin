package com.example.pics_impl

import com.best.core.mapper.Mapper
import com.example.pics_api.PicItem
import com.example.pics_api.PicsRepository
import java.net.UnknownHostException

internal class TestPicsRepositoryImpl(
    private val mapper: Mapper<List<PicItem>, List<PicDto>>
) : PicsRepository {

    private val dataList = mutableListOf<PicDto>()

    private var count = -1

    override fun getCachedData() = mapper.map(source=dataList)

    override suspend fun getData(): List<PicItem> {
        count++
        if (count % 3 == 0) throw UnknownHostException()
        dataList.addAll(generateList())
        return mapper.map(source = dataList)
    }

    private fun generateList(): List<PicDto> {
        val list = mutableListOf<PicDto>()
        repeat((0..10).count()) {
            val size = dataList.size + list.size
            list.add(
                PicDto(
                    id = size.toString(),
                    author = "stub author $size",
                    url = "stub",
                    downloadUrl = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
                )
            )
        }
        return list
    }
}