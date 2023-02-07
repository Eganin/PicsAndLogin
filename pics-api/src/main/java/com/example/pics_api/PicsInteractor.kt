package com.example.pics_api

interface PicsInteractor {

    fun getInitialData(): List<PicItem>

    suspend fun getData(): List<PicItem>

    fun needToLoadMoreData(lastVisibleItemPosition: Int): Boolean
}