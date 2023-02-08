package com.example.pics_api

import com.example.pics_api.models.PicItem

interface PicsInteractor {

    fun getInitialData(): List<PicItem>

    suspend fun getData(): List<PicItem>

    fun needToLoadMoreData(lastVisibleItemPosition: Int): Boolean
}