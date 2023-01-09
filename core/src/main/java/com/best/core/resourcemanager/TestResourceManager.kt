package com.best.core.resourcemanager

import com.best.core.exception.ExceptionType
import com.best.core.resourcemanager.ResourceManager

class TestResourceManager: ResourceManager {
    override fun getString(resId: Int)= "stub"

    override fun getString(resId: Int, vararg args: Any?)= "stub with args"

    override fun getErrorMessage(exceptionType: ExceptionType)=
        when (exceptionType) {
            ExceptionType.NETWORK_UNAVAILABLE -> "network is not available!"
            else -> "just generic error"
        }
}