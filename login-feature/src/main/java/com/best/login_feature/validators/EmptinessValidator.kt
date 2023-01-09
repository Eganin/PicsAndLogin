package com.best.login_feature.validators

class EmptinessValidator(errorMessage: String) :
    MinLengthValidator(errorMessage = errorMessage, minLength = 1)