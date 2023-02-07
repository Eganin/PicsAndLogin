package com.example.picsangloginapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.best.core.di.viewmodel.ViewModelFactory
import com.best.core.di.viewmodel.ViewModelKey
import com.best.core.exception.ExceptionHandler
import com.best.core.mapper.Mapper
import com.best.core.observer.ViewModelCommunication
import com.best.core.resourcemanager.ResourceManager
import com.example.picsangloginapp.data.pics.PicDto
import com.example.picsangloginapp.data.pics.PicsRepositoryImpl
import com.example.picsangloginapp.data.pics.PicsService
import com.example.picsangloginapp.domain.pics.*
import com.example.picsangloginapp.ui.pics.PicsUiMapper
import com.example.picsangloginapp.ui.pics.PicsViewModel
import com.example.picsangloginapp.ui.pics.adapter.PicUiModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module(
    includes = [
        RootModule.BindsModule::class
    ]
)
class RootModule {

    @Provides
    @RootScope
    fun provideViewModelCommunication(): ViewModelCommunication<List<PicUiModel>> =
        ViewModelCommunication.Base()

    @Provides
    @RootScope
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @RootScope
    fun providePicsService(converterFactory: GsonConverterFactory): PicsService = Retrofit.Builder()
        .baseUrl("https://picsum.photos/v2/")
        .addConverterFactory(converterFactory)
        .build()
        .create()

    @Provides
    @RootScope
    fun providePicsRepository(service: PicsService): PicsRepository =
        PicsRepositoryImpl(service = service)

    @Provides
    @RootScope
    fun providePicItemMapper(): Mapper<List<PicItem>, List<PicDto>> = PicItemMapper()

    @Provides
    @RootScope
    fun providePicsUiMapper(resourceManager: ResourceManager): Mapper<List<PicUiModel>, List<PicItem>> =
        PicsUiMapper(resourceManager = resourceManager)

    @Provides
    @RootScope
    fun providePicsInteractor(
        repository: PicsRepository,
        mapper: Mapper<List<PicItem>, List<PicDto>>,
        exceptionHandler: ExceptionHandler
    ): PicsInteractor = PicsInteractorImpl(
        repository = repository,
        mapper = mapper,
        exceptionHandler = exceptionHandler
    )

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