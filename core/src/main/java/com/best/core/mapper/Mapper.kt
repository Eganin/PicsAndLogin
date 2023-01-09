package com.best.core.mapper

interface Mapper<R, S> {

    fun map(source: S): R
}