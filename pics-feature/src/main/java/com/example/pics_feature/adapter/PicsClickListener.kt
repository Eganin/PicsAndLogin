package com.example.pics_feature.adapter

internal interface PicsClickListener {

    fun loadData()

    fun tryLoadDataAgain()

    fun tryLoadMoreDataAgain()

    fun onClick()
}