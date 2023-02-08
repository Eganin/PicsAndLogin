package com.best.login_feature.validators

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class PasswordValidatorTest {

    private val validator = PasswordValidator(errorMessage = "stub")

    @Test
    fun test_positive() {
        val validStrings = listOf("aA1", "2gW", "234sdfWER", "234SDFs", "23fsa=9!!%^&X")
        validStrings.forEach {
            assertThat(validator.isValid(text = it)).isTrue()
        }
    }

    @Test
    fun test_negative() {
        val invalidStrings =
            listOf("1234567", "qwerty", "QWERTY", "qwerty123", "QWERTY123", "QWERTYqwerty", "")
        invalidStrings.forEach {
            assertThat(validator.isValid(it)).isFalse()
        }
    }
}