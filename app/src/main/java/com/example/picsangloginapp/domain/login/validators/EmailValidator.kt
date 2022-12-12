package com.example.picsangloginapp.domain.login.validators

import androidx.core.util.PatternsCompat
import com.example.picsangloginapp.core.validation.UiValidator

class EmailValidator(errorMessage: String) : UiValidator.Abstract(errorMessage){
    override fun isValid(text: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(text).matches()
    }
}