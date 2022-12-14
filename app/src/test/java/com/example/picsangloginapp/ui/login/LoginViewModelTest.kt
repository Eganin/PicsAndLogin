package com.example.picsangloginapp.ui.login

import com.example.picsangloginapp.core.exception.ExceptionType
import com.example.picsangloginapp.core.validation.UiValidator
import com.best.login_api.LoginInteractor
import com.example.picsangloginapp.domain.login.WeatherItem
import com.example.picsangloginapp.domain.login.WeatherUiMapper
import com.example.picsangloginapp.ui.BaseUiTest
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class LoginViewModelTest: BaseUiTest() {

    @Test
    fun test_both_empty() {
        val communication = FakeCommunication.Base<com.best.login_feature.LoginState>()
        val interactor = FakeInteractor(item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val empty = FakeValidator(valid = false, message = "Input is empty")
        val viewModel = com.best.login_feature.LoginViewModel(
            communication = communication,
            interactor = interactor,
            mapper = FakeMapper(error = ""),
            validateEmail = empty,
            validatePassword = empty,
            dispatchers = TestDispatchersList()
        )

        viewModel.login(email = "", password = "")
        assertThat(
            com.best.login_feature.LoginState.TwoErrors(
                loginError = "Input is empty",
                passwordError = "Input is empty"
            )
        ).isEqualTo(communication.state())
    }

    @Test
    fun test_empty_email_valid_password() {
        val communication = FakeCommunication.Base<com.best.login_feature.LoginState>()
        val interactor = FakeInteractor(item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(valid = false, message = "Input is empty")
        val password = FakeValidator(valid = true, message = "")
        val viewModel = com.best.login_feature.LoginViewModel(
            communication = communication,
            interactor = interactor,
            mapper = FakeMapper(error = ""),
            validateEmail = email,
            validatePassword = password,
            dispatchers = TestDispatchersList()
        )

        viewModel.login(email = "", password = "asdfASDF123")
        assertThat(com.best.login_feature.LoginState.LoginError(value = "Input is empty")).isEqualTo(communication.state())
    }

    @Test
    fun test_invalid_email_valid_password() {
        val communication = FakeCommunication.Base<com.best.login_feature.LoginState>()
        val interactor = FakeInteractor(item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(valid = false, message = "Email is incorrect")
        val password = FakeValidator(valid = true, message = "")
        val viewModel = com.best.login_feature.LoginViewModel(
            communication = communication,
            interactor = interactor,
            mapper = FakeMapper(error = ""),
            validateEmail = email,
            validatePassword = password,
            dispatchers = TestDispatchersList()
        )

        viewModel.login(email = "succ", password = "asdfASDF123")
        assertThat(com.best.login_feature.LoginState.LoginError(value = "Email is incorrect")).isEqualTo(communication.state())
    }

    @Test
    fun test_valid_email() {
        val communication = FakeCommunication.Base<com.best.login_feature.LoginState>()
        val interactor = FakeInteractor(item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(valid = true, message = "")
        val password = FakeValidator(valid = false, message = "Input is empty")
        val viewModel = com.best.login_feature.LoginViewModel(
            communication = communication,
            interactor = interactor,
            mapper = FakeMapper(error = ""),
            validateEmail = email,
            validatePassword = password,
            dispatchers = TestDispatchersList()
        )
        viewModel.login(email = "erenjager@gmail.com", password = "")
        assertThat(com.best.login_feature.LoginState.PasswordError(value = "Input is empty")).isEqualTo(communication.state())
    }

    @Test
    fun test_password_invalid_length() {
        val communication = FakeCommunication.Base<com.best.login_feature.LoginState>()
        val interactor = FakeInteractor(item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(valid = true, message = "")
        val password =
            FakeValidator(valid = false, message = "Input should be at least 6 signs long")
        val viewModel = com.best.login_feature.LoginViewModel(
            communication = communication,
            interactor = interactor,
            mapper = FakeMapper(error = ""),
            validateEmail = email,
            validatePassword = password,
            dispatchers = TestDispatchersList()
        )
        viewModel.login(email = "erenjager@gmail.com", password = "asdf")
        assertThat(com.best.login_feature.LoginState.PasswordError(value = "Input should be at least 6 signs long")).isEqualTo(
            communication.state()
        )
    }

    @Test
    fun test_password_invalid() {
        val communication = FakeCommunication.Base<com.best.login_feature.LoginState>()
        val interactor = FakeInteractor(item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val email = FakeValidator(valid = true, message = "")
        val password =
            FakeValidator(
                valid = false,
                message = "Password should contain at least 1 lowercase letter, 1 uppercase letter and 1 digit"
            )
        val viewModel = com.best.login_feature.LoginViewModel(
            communication = communication,
            interactor = interactor,
            mapper = FakeMapper(error = ""),
            validateEmail = email,
            validatePassword = password,
            dispatchers = TestDispatchersList()
        )
        listOf("123456", "asdfgh", "ASDFGH", "!@#$%^&").forEach {
            viewModel.login(email = "erenjager@gmail.com", password = it)
            assertThat(
                com.best.login_feature.LoginState.PasswordError(value = "Password should contain at least 1 lowercase letter, 1 uppercase letter and 1 digit")
            ).isEqualTo(communication.state())
        }
    }

    @Test
    fun test_input_valid_no_connection() {
        val communication = FakeCommunication.Base<com.best.login_feature.LoginState>()
        val interactor = FakeInteractor(item = WeatherItem.Error(ExceptionType.NETWORK_UNAVAILABLE))
        val valid = FakeValidator(valid = true, message = "")
        val viewModel = com.best.login_feature.LoginViewModel(
            communication = communication,
            interactor = interactor,
            mapper = FakeMapper(error = "Network is unavailable"),
            validateEmail = valid,
            validatePassword = valid,
            dispatchers = TestDispatchersList()
        )

        viewModel.login(email = "erenjager@gmail.com", password = "asdfASDF1234")
        assertThat(communication.state()).isEqualTo(
            com.best.login_feature.LoginState.Error(
                value = com.best.login_feature.WeatherUiModel(
                    description = "Network is unavailable",
                    isError = true
                )
            )
        )
    }

    @Test
    fun test_input_valid() {
        val communication = FakeCommunication.Base<com.best.login_feature.LoginState>()
        val interactor = FakeInteractor(
            item = WeatherItem.Basic(
                description = "description",
                temp = 18,
                feelsLike = 20
            )
        )
        val valid = FakeValidator(valid = true, message = "")
        val viewModel = com.best.login_feature.LoginViewModel(
            communication = communication,
            interactor = interactor,
            mapper = FakeMapper(error = ""),
            validateEmail = valid,
            validatePassword = valid,
            dispatchers = TestDispatchersList()
        )
        viewModel.login(email = "erenjager@gmail.com", password = "asdfASDF1234")
        assertThat(communication.state()).isEqualTo(
            com.best.login_feature.LoginState.Success(
                value = com.best.login_feature.WeatherUiModel(
                    description = "description 18 20"
                )
            )
        )
    }

    private class FakeMapper(private val error: String) : WeatherUiMapper<com.best.login_feature.WeatherUiModel> {

        override fun map(feelsLike: Int, description: String, temp: Int): com.best.login_feature.WeatherUiModel {
            return com.best.login_feature.WeatherUiModel(description = "$description $temp $feelsLike")
        }

        override fun map(exceptionType: ExceptionType): com.best.login_feature.WeatherUiModel {
            return com.best.login_feature.WeatherUiModel(error, isError = true)
        }
    }

    private class FakeValidator(private val valid: Boolean, private val message: String) :
        UiValidator {

        override fun errorMessage() = message

        override fun isValid(text: String) = valid
    }

    private class FakeInteractor(
        private val item: WeatherItem
    ) : com.best.login_api.LoginInteractor {

        override suspend fun login() = item
    }
}