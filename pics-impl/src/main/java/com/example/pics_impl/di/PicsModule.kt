package com.example.pics_impl.di

import com.best.core.exception.ExceptionHandler
import com.best.core.mapper.Mapper
import com.example.pics_api.PicItem
import com.example.pics_api.PicsInteractor
import com.example.pics_api.PicsRepository
import com.example.pics_api.PicsService
import com.example.pics_api.di.PicScope
import com.example.pics_impl.PicDto
import com.example.pics_impl.PicItemMapper
import com.example.pics_impl.PicsInteractorImpl
import com.example.pics_impl.PicsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
internal class PicsModule {

    @Provides
    @PicScope
    fun providePicItemMapper(): Mapper<List<PicItem>, List<PicDto>> = PicItemMapper()

    @Provides
    @PicScope
    fun providePicsRepository(
        service: PicsService,
        mapper: Mapper<List<PicItem>, List<PicDto>>
    ): PicsRepository =
        PicsRepositoryImpl(service = service, mapper = mapper)

    @PicScope
    @Provides
    internal fun providePicsInteractor(
        repository: PicsRepository,
        exceptionHandler: ExceptionHandler
    ): PicsInteractor = PicsInteractorImpl(
        repository = repository,
        exceptionHandler = exceptionHandler
    )
}