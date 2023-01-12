package com.example.picsangloginapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EmailValidation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PasswordValidation