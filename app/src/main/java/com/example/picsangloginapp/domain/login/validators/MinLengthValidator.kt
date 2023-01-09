package com.example.picsangloginapp.domain.login.validators

import com.best.core.validation.UiValidator


open class MinLengthValidator(
    errorMessage: String,
    private val minLength: Int
) : UiValidator.Abstract(message = errorMessage) {
    override fun isValid(text: String) = text.length >= minLength
}