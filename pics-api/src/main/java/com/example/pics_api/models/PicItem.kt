package com.example.pics_api.models

import com.best.core.exception.ExceptionType

interface PicItemUiMapper<T> {

    fun map(text: String, url: String): T

    fun map(exceptionType: ExceptionType): T
}

sealed class PicItem {
    abstract fun <T> map(mapper: PicItemUiMapper<T>): T

    data class Base(private val text: String, val url: String) : PicItem() {
        override fun <T> map(mapper: PicItemUiMapper<T>) = mapper.map(text = text, url = url)
    }

    data class Error(private val exceptionType: ExceptionType = ExceptionType.GENERIC) : PicItem() {
        override fun <T> map(mapper: PicItemUiMapper<T>) = mapper.map(exceptionType = exceptionType)
    }
}