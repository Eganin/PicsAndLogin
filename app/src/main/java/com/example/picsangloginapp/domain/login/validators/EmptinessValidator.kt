package com.example.picsangloginapp.domain.login.validators

class EmptinessValidator(errorMessage: String) :
    MinLengthValidator(errorMessage = errorMessage, minLength = 1)