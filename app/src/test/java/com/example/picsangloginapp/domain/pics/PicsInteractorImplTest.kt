package com.example.picsangloginapp.domain.pics

import com.best.core.exception.ExceptionHandler
import com.best.core.exception.ExceptionHandlerImpl
import com.example.picsangloginapp.data.pics.PicDto
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
        val repository = TestPicsRepositoryImpl(getSuccess = true)
        val interactor = PicsInteractorImpl(repository, mapper, exceptionHandler)

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
        val repository = TestPicsRepositoryImpl(getSuccess = false)
        val interactor = PicsInteractorImpl(repository, mapper, exceptionHandler)

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

    private inner class TestPicsRepositoryImpl(private val getSuccess: Boolean) : PicsRepository {
        override fun getCachedData(): List<PicDto> {
            return mutableListOf<PicDto>().apply {
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
            }
        }

        override suspend fun getData(): List<PicDto> {
            return if (getSuccess) mutableListOf<PicDto>().apply {
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
            } else throw UnknownHostException()
        }
    }
}