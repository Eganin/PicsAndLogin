package com.best.core.resourcemanager

import android.content.Context
import com.best.core.R

import com.best.core.exception.ExceptionType

class ResourceManagerImpl(private val context: Context) : ResourceManager {

    override fun getString(resId: Int) = context.getString(resId)

    override fun getString(resId: Int, vararg args: Any?) = context.getString(resId, *args)

    override fun getErrorMessage(exceptionType: ExceptionType) =
        getString(
            when (exceptionType) {
                ExceptionType.NETWORK_UNAVAILABLE -> R.string.network_exception
                else -> R.string.some_exception
            }
        )
}