package com.best.core.resourcemanager

import androidx.annotation.StringRes
import com.best.core.exception.ExceptionType

interface ResourceManager {

    fun getString(@StringRes resId : Int) : String

    fun getString(@StringRes resId : Int, vararg args : Any?): String

    fun getErrorMessage(exceptionType: ExceptionType) : String
}