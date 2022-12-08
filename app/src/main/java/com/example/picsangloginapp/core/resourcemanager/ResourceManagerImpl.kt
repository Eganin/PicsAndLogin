package com.example.picsangloginapp.core.resourcemanager

import android.content.Context
import com.example.picsangloginapp.R
import com.example.picsangloginapp.core.exception.ExceptionType

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