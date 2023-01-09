package com.example.picsangloginapp.domain.pics

import com.best.core.mapper.Mapper
import com.example.picsangloginapp.data.pics.PicDto

class PicItemMapper : Mapper<List<PicItem>, List<PicDto>> {

    override fun map(source: List<PicDto>) =
        source.map {
            PicItem.Base(
                text = it.author,
                url = it.downloadUrl
            )
        }
}