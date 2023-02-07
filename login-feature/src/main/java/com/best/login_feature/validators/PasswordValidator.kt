package com.best.login_feature.validators

import com.best.core.validation.UiValidator
import java.util.regex.Pattern

internal class PasswordValidator(
    errorMessage: String
) : UiValidator.Abstract(message = errorMessage) {
    override fun isValid(text: String) =
        Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{3,}")
            .matcher(text)
            .matches()
}