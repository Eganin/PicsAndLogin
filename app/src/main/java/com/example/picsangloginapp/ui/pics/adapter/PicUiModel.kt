package com.example.picsangloginapp.ui.pics.adapter

import com.example.picsangloginapp.core.other.ImageContainer
import com.example.picsangloginapp.core.other.TextContainer

abstract class PicUiModel(val type: PicUiModelType) {
    open fun show(textContainer: TextContainer) {}
    open fun show(textContainer: TextContainer, imageContainer: ImageContainer) {}
}

enum class PicUiModelType {
    BASIC,
    FULL_SIZE_LOADER,
    BOTTOM_LOADER,
    FULL_SIZE_ERROR,
    BOTTOM_ERROR
}