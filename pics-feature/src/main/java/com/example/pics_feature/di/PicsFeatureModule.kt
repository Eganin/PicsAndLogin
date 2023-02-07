package com.example.pics_feature.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.best.core.di.viewmodel.ViewModelFactory
import com.best.core.di.viewmodel.ViewModelKey
import com.best.core.mapper.Mapper
import com.best.core.observer.ViewModelCommunication
import com.best.core.resourcemanager.ResourceManager
import com.example.pics_api.PicItem
import com.example.pics_feature.PicsUiMapper
import com.example.pics_feature.PicsViewModel
import com.example.pics_feature.adapter.PicUiModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [PicsFeatureModule.BindsModule::class])
internal class PicsFeatureModule {

    @Provides
    @PicsFeatureScope
    fun provideViewModelCommunication(): ViewModelCommunication<List<PicUiModel>> =
        ViewModelCommunication.Base()

    @Provides
    @PicsFeatureScope
    fun providePicsUiMapper(resourceManager: ResourceManager): Mapper<List<PicUiModel>, List<PicItem>> =
        PicsUiMapper(resourceManager = resourceManager)

    @Module
    interface BindsModule {
        @Binds
        @IntoMap
        @ViewModelKey(PicsViewModel::class)
        fun bindPicsViewModel(viewModel: PicsViewModel): ViewModel

        @PicsFeatureScope
        @Binds
        fun provideViewModelFactory(commonViewModelFactory: ViewModelFactory): ViewModelProvider.Factory
    }
}