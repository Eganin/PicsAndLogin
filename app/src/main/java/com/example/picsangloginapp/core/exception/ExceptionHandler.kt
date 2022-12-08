package com.example.picsangloginapp.core.exception

interface ExceptionHandler {

    fun defineExceptionType(e : Exception) : ExceptionType
}