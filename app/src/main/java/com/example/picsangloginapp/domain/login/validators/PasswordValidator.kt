package com.example.picsangloginapp.domain.login.validators

import com.example.picsangloginapp.core.validation.UiValidator
import java.util.regex.Pattern

class PasswordValidator(
    errorMessage: String
) : UiValidator.Abstract(message = errorMessage) {
    override fun isValid(text: String) =
        Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\W]{3,}")
            .matcher(text)
            .matches()
}