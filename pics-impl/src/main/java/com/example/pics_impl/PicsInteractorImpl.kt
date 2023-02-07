package com.example.pics_impl

import com.best.core.exception.ExceptionHandler
import com.best.core.mapper.Mapper
import com.example.pics_api.PicItem
import com.example.pics_api.PicsInteractor
import com.example.pics_api.PicsRepository
import javax.inject.Inject

class PicsInteractorImpl @Inject constructor(
    private val repository: PicsRepository,
    private val exceptionHandler: ExceptionHandler
) : PicsInteractor {
    override fun getInitialData() = repository.getCachedData()

    override suspend fun getData() = try {
        repository.getData()
    } catch (e: Exception) {
        mutableListOf<PicItem>().apply {
            addAll(getInitialData())
            add(PicItem.Error(exceptionType = exceptionHandler.defineExceptionType(e)))
        }
    }

    override fun needToLoadMoreData(lastVisibleItemPosition: Int): Boolean =
        with(repository.getCachedData()) {
            return isNotEmpty() && size - 1 == lastVisibleItemPosition
        }
}