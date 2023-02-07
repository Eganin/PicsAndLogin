package com.example.picsangloginapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.best.core.di.viewmodel.ViewModelFactory
import com.best.core.di.viewmodel.ViewModelKey
import com.best.core.mapper.Mapper
import com.best.core.observer.ViewModelCommunication
import com.best.core.resourcemanager.ResourceManager
import com.example.pics_api.*
import com.example.pics_impl.*
import com.example.picsangloginapp.data.RootInteractorImpl
import com.example.picsangloginapp.domain.RootInteractor
import com.example.picsangloginapp.ui.pics.PicsUiMapper
import com.example.picsangloginapp.ui.pics.PicsViewModel
import com.example.picsangloginapp.ui.pics.adapter.PicUiModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        RootModule.BindsModule::class
    ]
)
class RootModule {

    @Provides
    @RootScope
    fun provideRootInteractor(): RootInteractor = RootInteractorImpl()

    @Provides
    @RootScope
    fun provideViewModelCommunication(): ViewModelCommunication<List<PicUiModel>> =
        ViewModelCommunication.Base()

    @Provides
    @RootScope
    fun providePicsUiMapper(resourceManager: ResourceManager): Mapper<List<PicUiModel>, List<PicItem>> =
        PicsUiMapper(resourceManager = resourceManager)


    @Module
    interface BindsModule {
        @Binds
        @IntoMap
        @ViewModelKey(PicsViewModel::class)
        fun bindPicsViewModel(viewModel: PicsViewModel): ViewModel

        @RootScope
        @Binds
        fun provideViewModelFactory(commonViewModelFactory: ViewModelFactory): ViewModelProvider.Factory
    }
}