package com.example.picsangloginapp.di

import com.best.core.di.CoreComponent
import com.example.picsangloginapp.ui.MainFragment
import dagger.Component

@Component(
    modules = [RootModule::class],
    dependencies = [CoreComponent::class]
)
@RootScope
interface RootComponent {

    fun inject(mainFragment: MainFragment)
}