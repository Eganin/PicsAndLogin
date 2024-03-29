package com.example.pics_feature

import com.best.core.exception.ExceptionType
import com.best.core.mapper.Mapper
import com.best.core.resourcemanager.ResourceManager
import com.example.pics_api.models.PicItem
import com.example.pics_api.models.PicItemUiMapper
import com.example.pics_feature.adapter.*

internal class PicsUiMapper(resourceManager: ResourceManager) : Mapper<List<PicUiModel>, List<PicItem>> {

    private val fullSizeErrorMapper =
        PicUiMapper(type = PicUiModelType.FULL_SIZE_ERROR, resourceManager = resourceManager)

    private val bottomError =
        PicUiMapper(type = PicUiModelType.BOTTOM_ERROR, resourceManager = resourceManager)

    private val baseMapper =
        PicUiMapper(type = PicUiModelType.BASIC, resourceManager = resourceManager)

    override fun map(source: List<PicItem>): List<PicUiModel> {
        val result = mutableListOf<PicUiModel>()
        when {
            source.isEmpty() -> result.add(FullSizeLoader)
            source.size == 1 && source[0] is PicItem.Error -> result.add(source[0].map(mapper = fullSizeErrorMapper))
            source.last() is PicItem.Base -> {
                result.addAll(
                    source.map {
                        it.map(mapper = baseMapper)
                    }
                )
                result.add(BottomLoader)
            }
            source.last() is PicItem.Error -> {
                source.forEach { item ->
                    if (item is PicItem.Base) result.add(item.map(mapper = baseMapper))
                }
                result.add(source.last().map(mapper = bottomError))
            }
        }
        return result
    }
}

internal class PicUiMapper(
    private val type: PicUiModelType,
    private val resourceManager: ResourceManager
) : PicItemUiMapper<PicUiModel> {

    override fun map(text: String, url: String): PicUiModel = if (type == PicUiModelType.BASIC)
        Basic(text = text, url = url)
    else throw IllegalArgumentException("using basic mapper for error")

    override fun map(exceptionType: ExceptionType): PicUiModel =
        resourceManager.getErrorMessage(exceptionType = exceptionType).let {
            if (type == PicUiModelType.FULL_SIZE_ERROR)
                FullSizeError(message = it)
            else
                BottomError(message = it)
        }
}