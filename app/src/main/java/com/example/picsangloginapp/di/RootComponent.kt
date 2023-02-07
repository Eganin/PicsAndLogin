package com.example.picsangloginapp.di

import com.best.core.di.CoreComponent
import com.best.core.di.viewmodel.VmFactoryWrapper
import com.example.picsangloginapp.ui.MainActivity
import com.example.picsangloginapp.ui.MainFragment
import com.example.picsangloginapp.ui.pics.PicsFragment
import dagger.Component

@Component(
    modules = [RootModule::class],
    dependencies =[CoreComponent::class]
)
@RootScope
interface RootComponent {
    fun inject(vmFactoryWrapper: VmFactoryWrapper)

    fun inject(mainFragment: MainFragment)
}