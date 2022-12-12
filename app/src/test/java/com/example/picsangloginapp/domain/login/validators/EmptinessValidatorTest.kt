package com.example.picsangloginapp.domain.login.validators

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class EmptinessValidatorTest{

    private val validator = EmptinessValidator(errorMessage = "stub")

    @Test
    fun test_positive(){
        val validStrings = listOf("a","12"," ","s -d","'")
        validStrings.forEach {
            assertThat(validator.isValid(text = it)).isTrue()
        }
    }

    @Test
    fun test_negative(){
        val invalidString =""
        assertThat(validator.isValid(text = invalidString)).isFalse()
    }
}