package com.best.login_feature.validators

internal class EmptinessValidator(errorMessage: String) :
    MinLengthValidator(errorMessage = errorMessage, minLength = 1)