package com.example.pics_impl

import com.best.core.mapper.Mapper
import com.example.pics_api.models.PicItem
import com.example.pics_impl.models.PicDto

internal class PicItemMapper : Mapper<List<PicItem>, List<PicDto>> {

    override fun map(source: List<PicDto>) =
        source.map {
            PicItem.Base(
                text = it.author,
                url = it.downloadUrl
            )
        }
}