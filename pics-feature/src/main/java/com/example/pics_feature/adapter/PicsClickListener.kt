package com.example.pics_feature.adapter

interface PicsClickListener {

    fun loadData()

    fun tryLoadDataAgain()

    fun tryLoadMoreDataAgain()

    fun onClick()
}