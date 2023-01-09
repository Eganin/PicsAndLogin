package com.best.login_api.di

import com.best.core.di.BaseComponentHolderCore

abstract class LoginComponentHolder :
    BaseComponentHolderCore.Dependent<LoginComponent, LoginComponentDependencies>()