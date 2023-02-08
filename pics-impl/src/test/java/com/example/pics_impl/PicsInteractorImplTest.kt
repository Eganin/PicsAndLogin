package com.example.pics_impl

import com.best.core.exception.ExceptionHandler
import com.best.core.exception.ExceptionHandlerImpl
import com.best.core.mapper.Mapper
import com.example.pics_api.PicsRepository
import com.example.pics_api.models.PicItem
import com.example.pics_impl.models.PicDto
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

@OptIn(ExperimentalCoroutinesApi::class)
class PicsInteractorImplTest {

    private lateinit var mapper: PicItemMapper
    private lateinit var exceptionHandler: ExceptionHandler

    @Before
    fun setup() {
        mapper = PicItemMapper()
        exceptionHandler = ExceptionHandlerImpl()
    }

    @Test
    fun test_positive_case() = runTest {
        val repository = TestPicsRepositoryImpl(getSuccess = true, mapper = mapper)
        val interactor = PicsInteractorImpl(repository, exceptionHandler)

        val initialData = interactor.getInitialData()
        assertThat(initialData).hasSize(6)

        var needLoadMoreData = interactor.needToLoadMoreData(lastVisibleItemPosition = 5)
        assertThat(needLoadMoreData).isTrue()

        (0..4).forEach { i ->
            needLoadMoreData = interactor.needToLoadMoreData(lastVisibleItemPosition = i)
            assertThat(needLoadMoreData).isFalse()
        }

        val data = interactor.getData()
        assertThat(data).hasSize(10)
        data.forEach { item ->
            assertThat(item is PicItem.Base).isTrue()
        }
    }

    @Test
    fun test_negative_case() = runTest {
        val repository = TestPicsRepositoryImpl(getSuccess = false, mapper = mapper)
        val interactor = PicsInteractorImpl(repository, exceptionHandler)

        val initialData = interactor.getInitialData()
        assertThat(initialData).hasSize(0)

        (0..5).forEach { i ->
            val needLoadMoreData = interactor.needToLoadMoreData(lastVisibleItemPosition = i)
            assertThat(needLoadMoreData).isFalse()
        }

        val data = interactor.getData()
        assertThat(data).hasSize(1)
        assertThat(data[0] is PicItem.Error).isTrue()
    }

    private inner class TestPicsRepositoryImpl(
        private val getSuccess: Boolean,
        private val mapper: Mapper<List<PicItem>, List<PicDto>>
    ) : PicsRepository {
        override fun getCachedData(): List<PicItem> {
            return mapper.map(source = mutableListOf<PicDto>().apply {
                if (getSuccess) {
                    (0..5).forEach { i ->
                        add(
                            PicDto(
                                id = "id$i",
                                author = "author$i",
                                url = "url$i",
                                downloadUrl = "url2$i"
                            )
                        )
                    }
                }
            })
        }

        override suspend fun getData(): List<PicItem> {
            return mapper.map(source = if (getSuccess) mutableListOf<PicDto>().apply {
                (0..9).forEach { i ->
                    add(
                        PicDto(
                            id = "id$i",
                            author = "author$i",
                            url = "url$i",
                            downloadUrl = "url2$i"
                        )
                    )
                }
            } else throw UnknownHostException())
        }
    }
}