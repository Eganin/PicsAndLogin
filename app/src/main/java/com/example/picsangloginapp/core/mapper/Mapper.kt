package com.example.picsangloginapp.core.mapper

interface Mapper<R, S> {

    fun map(source: S): R
}