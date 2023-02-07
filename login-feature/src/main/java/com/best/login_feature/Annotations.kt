package com.best.login_feature

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class EmailValidation

@Qualifier
@Retention(AnnotationRetention.BINARY)
internal annotation class PasswordValidation