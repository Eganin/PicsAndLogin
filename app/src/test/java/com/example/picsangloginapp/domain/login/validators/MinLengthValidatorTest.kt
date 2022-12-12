package com.example.picsangloginapp.domain.login.validators

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class MinLengthValidatorTest {

    private lateinit var validator: MinLengthValidator

    @Test
    fun test_positive_two_sings() {
        validator = MinLengthValidator(errorMessage = "stub", minLength = 2)

        val validStrings = listOf("12", "  ", " 2", "2 ", "123", "234r5", "as;ldkfs")
        validStrings.forEach {
            assertThat(validator.isValid(text = it)).isTrue()
        }
    }

    @Test
    fun test_positive_three_sings() {
        validator = MinLengthValidator(errorMessage = "stub", minLength = 3)

        val validStrings = listOf("1 2", " _ ", " 2 ", "2 3", "1234", "234 r5", "as;ldk asdf s")

        validStrings.forEach {
            assertThat(validator.isValid(text = it)).isTrue()
        }
    }

    @Test
    fun test_negative() {
        validator = MinLengthValidator(errorMessage = "stub", minLength = 8)

        val invalidStrings = listOf("123", " ", "123231", "dsafr")

        invalidStrings.forEach {
            assertThat(validator.isValid(text = it)).isFalse()
        }
    }
}