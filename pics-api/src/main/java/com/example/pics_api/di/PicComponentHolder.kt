package com.example.pics_api.di

import com.best.core.di.BaseComponentHolderCore

abstract class PicComponentHolder :
    BaseComponentHolderCore.Dependent<PicComponent, PicComponentDependencies>()