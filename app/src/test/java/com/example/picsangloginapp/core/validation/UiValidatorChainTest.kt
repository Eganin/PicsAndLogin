package com.example.picsangloginapp.core.validation

import com.best.core.validation.UiValidator
import com.best.core.validation.UiValidatorChain
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class UiValidatorChainTest {

    @Test
    fun test_two_validators_all_valid() {
        val chain = UiValidatorChain(
            currentChain = TestUiValidator(
                errorMessage = "first error message",
                validText = "validOne"
            ),
            nextChain = TestUiValidator(
                errorMessage = "second error message",
                validText = "validOne"
            )
        )

        assertThat(chain.isValid(text = "validOne")).isTrue()
    }

    @Test
    fun test_two_validators_first_is_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator(
                errorMessage = "first error message",
                validText = "validOne"
            ),
            TestUiValidator(
                errorMessage = "second error message",
                validText = "validOne"
            )
        )

        assertThat(chain.isValid(text = "invalid")).isFalse()
        assertThat(chain.errorMessage()).isEqualTo("first error message")
    }

    @Test
    fun test_two_validators_second_is_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator(
                errorMessage = "first error message",
                validText = "validOne"
            ),
            TestUiValidator(
                errorMessage = "second error message",
                validText = "validTwo"
            )
        )

        assertThat(chain.isValid(text = "validOne")).isFalse()
        assertThat(chain.errorMessage()).isEqualTo("second error message")
    }

    @Test
    fun test_three_validators_all_are_valid() {
        val chain = UiValidatorChain(
            TestUiValidator(
                errorMessage = "first error message",
                validText = "validOne"
            ),
            TestUiValidator(
                errorMessage = "second error message",
                validText = "validOne"
            )
        )

        assertThat(chain.isValid(text = "validOne")).isTrue()
    }

    @Test
    fun test_three_validators_first_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            UiValidatorChain(
                TestUiValidator("second error message", "validOne"),
                TestUiValidator("third error message", "validOne")
            )
        )

        assertThat(chain.isValid(text="invalid")).isFalse()
        assertThat(chain.errorMessage()).isEqualTo("first error message")
    }

    @Test
    fun test_three_validators_second_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            UiValidatorChain(
                TestUiValidator("second error message", "validTwo"),
                TestUiValidator("third error message", "validOne")
            )
        )

        assertThat(chain.isValid(text="validOne")).isFalse()
        assertThat(chain.errorMessage()).isEqualTo("second error message")
    }

    @Test
    fun test_three_validators_third_invalid() {
        val chain = UiValidatorChain(
            TestUiValidator("first error message", "validOne"),
            UiValidatorChain(
                TestUiValidator("second error message", "validOne"),
                TestUiValidator("third error message", "validThree")
            )
        )

        assertThat(chain.isValid(text="validOne")).isFalse()
        assertThat(chain.errorMessage()).isEqualTo("third error message")
    }

    private inner class TestUiValidator(
        errorMessage: String,
        private val validText: String
    ) : UiValidator.Abstract(message = errorMessage) {
        override fun isValid(text: String) = validText == text
    }
}
