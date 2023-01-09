package com.example.picsangloginapp.domain.pics

import com.best.core.exception.ExceptionHandler
import com.best.core.mapper.Mapper
import com.example.picsangloginapp.data.pics.PicDto
import javax.inject.Inject

class PicsInteractorImpl @Inject constructor(
    private val repository: PicsRepository,
    private val mapper: Mapper<List<PicItem>, List<PicDto>>,
    private val exceptionHandler: ExceptionHandler
) : PicsInteractor {
    override fun getInitialData() = mapper.map(source = repository.getCachedData())

    override suspend fun getData() = try {
        mapper.map(source = repository.getData())
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