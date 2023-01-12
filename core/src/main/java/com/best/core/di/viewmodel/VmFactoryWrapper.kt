package com.best.core.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class VmFactoryWrapper {
    @Inject
    lateinit var factory: ViewModelProvider.Factory
}