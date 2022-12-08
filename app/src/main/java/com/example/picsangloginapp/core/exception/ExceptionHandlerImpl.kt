package com.example.picsangloginapp.core.exception

import java.net.UnknownHostException

class ExceptionHandlerImpl: ExceptionHandler {
    override fun defineExceptionType(e: Exception)= when(e){
        is UnknownHostException -> ExceptionType.NETWORK_UNAVAILABLE
        else -> ExceptionType.GENERIC
    }
}