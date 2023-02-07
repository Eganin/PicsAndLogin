package com.example.picsangloginapp.domain

import com.best.core.Feature

interface RootInteractor {
    fun initFeature(feature: Feature)
    fun resetFeature(feature: Feature)
}