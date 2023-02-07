package com.example.pics_feature.adapter

import com.best.core.other.ImageContainer
import com.best.core.other.TextContainer

internal abstract class PicUiModel(val type: PicUiModelType) {
    open fun show(textContainer: TextContainer) {}
    open fun show(textContainer: TextContainer, imageContainer: ImageContainer) {}
}

internal data class Basic(private val text: String, private val url: String) :
    PicUiModel(type = PicUiModelType.BASIC) {

    override fun show(textContainer: TextContainer, imageContainer: ImageContainer) {
        textContainer.show(text = text)
        imageContainer.show(url = url)
    }
}

internal object FullSizeLoader : PicUiModel(type = PicUiModelType.FULL_SIZE_LOADER)

internal object BottomLoader : PicUiModel(type = PicUiModelType.BOTTOM_LOADER)

internal abstract class AnyError(private val message: String, type: PicUiModelType) :
    PicUiModel(type = type) {
    override fun show(textContainer: TextContainer) = textContainer.show(text = message)
}

internal data class FullSizeError(private val message: String) :
    AnyError(message = message, type = PicUiModelType.FULL_SIZE_ERROR)

internal data class BottomError(private val message: String) :
    AnyError(message = message, type = PicUiModelType.BOTTOM_ERROR)

internal enum class PicUiModelType {
    BASIC,
    FULL_SIZE_LOADER,
    BOTTOM_LOADER,
    FULL_SIZE_ERROR,
    BOTTOM_ERROR
}