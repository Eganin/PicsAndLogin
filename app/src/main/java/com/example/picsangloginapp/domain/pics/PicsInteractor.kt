package com.example.picsangloginapp.domain.pics

interface PicsInteractor {

    fun getInitialData(): List<PicItem>

    suspend fun getData(): List<PicItem>

    fun needToLoadMoreData(lastVisibleItemPosition: Int): Boolean
}