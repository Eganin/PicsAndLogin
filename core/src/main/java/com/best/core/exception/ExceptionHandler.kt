package com.best.core.exception

interface ExceptionHandler {

    fun defineExceptionType(e : Exception) : ExceptionType
}