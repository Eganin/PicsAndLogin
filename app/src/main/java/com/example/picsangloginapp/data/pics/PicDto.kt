package com.example.picsangloginapp.data.pics

import com.google.gson.annotations.SerializedName

data class PicDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)
