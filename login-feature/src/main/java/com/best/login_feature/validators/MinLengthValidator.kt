package com.best.login_feature.validators

import com.best.core.validation.UiValidator

internal open class MinLengthValidator(
    errorMessage: String,
    private val minLength: Int
) : UiValidator.Abstract(message = errorMessage) {
    override fun isValid(text: String) = text.length >= minLength
}