package com.example.picsangloginapp.ui.pics.adapter

import com.example.picsangloginapp.core.other.ImageContainer
import com.example.picsangloginapp.core.other.TextContainer

abstract class PicUiModel(val type: PicUiModelType) {
    open fun show(textContainer: TextContainer) {}
    open fun show(textContainer: TextContainer, imageContainer: ImageContainer) {}
}

data class Basic(private val text: String, private val url: String) :
    PicUiModel(type = PicUiModelType.BASIC) {

    override fun show(textContainer: TextContainer, imageContainer: ImageContainer) {
        textContainer.show(text = text)
        imageContainer.show(url = url)
    }
}

object FullSizeLoader : PicUiModel(type = PicUiModelType.FULL_SIZE_LOADER)

object BottomLoader : PicUiModel(type = PicUiModelType.BOTTOM_LOADER)

abstract class AnyError(private val message: String, type: PicUiModelType) :
    PicUiModel(type = type) {
    override fun show(textContainer: TextContainer) = textContainer.show(text = message)
}

data class FullSizeError(private val message: String) :
    AnyError(message = message, type = PicUiModelType.FULL_SIZE_ERROR)

data class BottomError(private val message: String) :
    AnyError(message = message, type = PicUiModelType.BOTTOM_ERROR)

enum class PicUiModelType {
    BASIC,
    FULL_SIZE_LOADER,
    BOTTOM_LOADER,
    FULL_SIZE_ERROR,
    BOTTOM_ERROR
}