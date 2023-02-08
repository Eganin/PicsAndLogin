package com.example.pics_impl.models

import com.google.gson.annotations.SerializedName

internal data class PicDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)
