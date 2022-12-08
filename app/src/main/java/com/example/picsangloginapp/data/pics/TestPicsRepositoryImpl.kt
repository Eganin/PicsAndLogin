package com.example.picsangloginapp.data.pics

import com.example.picsangloginapp.domain.pics.PicsRepository
import java.net.UnknownHostException

class TestPicsRepositoryImpl : PicsRepository {

    private val dataList = mutableListOf<PicDto>()

    private var count = -1

    override fun getCachedData() = dataList

    override suspend fun getData(): List<PicDto> {
        count++
        if (count % 3 == 0) throw UnknownHostException()
        dataList.addAll(generateList())
        return dataList
    }

    private fun generateList(): List<PicDto> {
        val list = mutableListOf<PicDto>()
        (0..10).forEach { i ->
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