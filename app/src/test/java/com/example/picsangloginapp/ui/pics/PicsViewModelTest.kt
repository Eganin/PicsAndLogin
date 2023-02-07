package com.example.picsangloginapp.ui.pics

import com.best.core.exception.ExceptionType
import com.best.core.resourcemanager.TestResourceManager
import com.example.pics_api.PicItem
import com.example.pics_api.PicsInteractor
import com.example.pics_feature.PicsUiMapper
import com.example.pics_feature.PicsViewModel
import com.example.pics_feature.adapter.*
import com.example.picsangloginapp.ui.BaseUiTest
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class PicsViewModelTest : BaseUiTest() {

    private val mapper = PicsUiMapper(resourceManager = TestResourceManager())

    @Test
    fun test_get_empty_initial_data() {
        val communication = FakeCommunication.Base<List<PicUiModel>>()
        val interactor = FakeInteractor(isError = false)
        PicsViewModel(
            communication = communication,
            interactor = interactor,
            dispatchers = TestDispatchersList(),
            mapper = mapper
        )

        assertThat(communication.state()).isEqualTo(listOf(FullSizeLoader))
    }

    @Test
    fun test_valid_load_data() {
        val communication = FakeCommunication.Base<List<PicUiModel>>()
        val interactor = FakeInteractor(isError = false)
        val viewModel = PicsViewModel(
            communication = communication,
            interactor = interactor,
            dispatchers = TestDispatchersList(),
            mapper = mapper
        )
        viewModel.loadData()
        assertThat(communication.state()).isEqualTo(
            listOf(
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                BottomLoader
            )
        )
    }

    @Test
    fun test_invalid_load_data() {
        val communication = FakeCommunication.Base<List<PicUiModel>>()
        val interactor = FakeInteractor(isError = true)
        val viewModel = PicsViewModel(
            communication = communication,
            interactor = interactor,
            dispatchers = TestDispatchersList(),
            mapper = mapper
        )
        viewModel.loadData()
        assertThat(communication.state()).isEqualTo(
            listOf(
                FullSizeError(message = "just generic error")
            )
        )
    }

    @Test
    fun test_valid_load_more_data() {
        val communication = FakeCommunication.Base<List<PicUiModel>>()
        val interactor = FakeInteractor(isError = false)
        val viewModel = PicsViewModel(
            communication = communication,
            interactor = interactor,
            dispatchers = TestDispatchersList(),
            mapper = mapper
        )
        viewModel.loadData()
        val lastVisibleItemPosition = 2
        val loadMoreData =
            interactor.needToLoadMoreData(lastVisibleItemPosition = lastVisibleItemPosition)
        assertThat(loadMoreData).isTrue()
        viewModel.loadMoreData(lastVisibleItemPosition = lastVisibleItemPosition)
        println(communication.state())
        assertThat(communication.state().size).isEqualTo(7)
        assertThat(communication.state()).isEqualTo(
            listOf(
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                BottomLoader
            )
        )
    }

    @Test
    fun test_invalid_more_load_data() {
        val communication = FakeCommunication.Base<List<PicUiModel>>()
        val interactor = FakeInteractor(isError = false)
        val viewModel = PicsViewModel(
            communication = communication,
            interactor = interactor,
            dispatchers = TestDispatchersList(),
            mapper = mapper
        )
        viewModel.loadData()
        val loadMoreData = interactor.needToLoadMoreData(lastVisibleItemPosition = 1)
        assertThat(loadMoreData).isFalse()
        assertThat(communication.state().size).isEqualTo(4)
        assertThat(communication.state()).isEqualTo(
            listOf(
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                Basic(text = "stub", url = "https:/google.com"),
                BottomLoader
            )
        )
    }

    @Test
    fun test_get_full_initial_data() {
        val communication = FakeCommunication.Base<List<PicUiModel>>()
        val interactor = FakeInteractor(isError = false)
        val viewModel = PicsViewModel(
            communication = communication,
            interactor = interactor,
            dispatchers = TestDispatchersList(),
            mapper = mapper
        )
        viewModel.loadData()
        assertThat(interactor.getInitialData()).isEqualTo(
            listOf(
                PicItem.Base(text = "stub", url = "https:/google.com"),
                PicItem.Base(text = "stub", url = "https:/google.com"),
                PicItem.Base(text = "stub", url = "https:/google.com")
            )
        )
    }

    @Test
    fun test_get_initial_data_if_error() {
        val communication = FakeCommunication.Base<List<PicUiModel>>()
        val interactor = FakeInteractor(isError = true)
        val viewModel = PicsViewModel(
            communication = communication,
            interactor = interactor,
            dispatchers = TestDispatchersList(),
            mapper = mapper
        )
        viewModel.loadData()
        assertThat(interactor.getInitialData()).isEqualTo(listOf(PicItem.Error()))
    }

    private class FakeInteractor(private val isError: Boolean = false) : PicsInteractor {

        private val data = mutableListOf<PicItem>()

        override fun getInitialData() = data

        override suspend fun getData(): List<PicItem> {
            if (!isError)
                data.addAll(
                    listOf(
                        PicItem.Base(text = "stub", url = "https:/google.com"),
                        PicItem.Base(text = "stub", url = "https:/google.com"),
                        PicItem.Base(text = "stub", url = "https:/google.com")
                    )
                )
            else {
                data.clear()
                data.add(
                    PicItem.Error(exceptionType = ExceptionType.GENERIC)
                )
            }
            return data
        }

        override fun needToLoadMoreData(lastVisibleItemPosition: Int): Boolean {
            return with(data) {
                isNotEmpty() && size - 1 == lastVisibleItemPosition
            }
        }
    }

}