package com.best.core.validation

interface UiValidator {

    fun errorMessage(): String

    fun isValid(text: String): Boolean

    abstract class Abstract(private val message: String) : UiValidator {
        override fun errorMessage() = message
    }
}