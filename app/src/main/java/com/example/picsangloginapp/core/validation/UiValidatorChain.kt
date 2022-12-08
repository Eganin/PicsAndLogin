package com.example.picsangloginapp.core.validation

class UiValidatorChain(
    private val currentChain: UiValidator,
    private val nextChain: UiValidator
) : UiValidator {

    private var baseValid = false

    override fun errorMessage() =
        if (baseValid) nextChain.errorMessage() else currentChain.errorMessage()

    override fun isValid(text: String): Boolean {
        baseValid = currentChain.isValid(text = text)
        return if (baseValid) nextChain.isValid(text = text) else false
    }
}