package com.example.picsangloginapp.domain.login.validators

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class EmailValidatorTest{

    private val validator = EmailValidator(errorMessage = "stub")

    @Test
    fun test_positive(){
        val validStrings = listOf("erenJager@gmail.com","sd@yandex.ru","2314d@ya.ru")
        validStrings.forEach {
            assertThat(validator.isValid(text = it)).isTrue()
        }
    }

    @Test
    fun test_negative(){
        val invalidStrings = listOf("stub","succ","zazzaza@")
        invalidStrings.forEach {
            assertThat(validator.isValid(text = it)).isFalse()
        }
    }
}