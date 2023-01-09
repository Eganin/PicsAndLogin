package com.example.picsangloginapp.ui.pics

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.best.core.dispatchers.DispatchersList
import com.best.core.mapper.Mapper
import com.best.core.observer.Observe
import com.best.core.observer.ViewModelCommunication
import com.example.picsangloginapp.domain.pics.PicItem
import com.example.picsangloginapp.domain.pics.PicsInteractor
import com.example.picsangloginapp.ui.pics.adapter.PicUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicsViewModel @Inject constructor(
    private val communication: ViewModelCommunication<List<PicUiModel>>,
    private val interactor: PicsInteractor,
    private val mapper: Mapper<List<PicUiModel>, List<PicItem>>,
    private val dispatchers: DispatchersList
) : ViewModel(), Observe<List<PicUiModel>> {

    private var lastVisibleItemPos = -1

    init {
        communication.map(source = mapper.map(source = interactor.getInitialData()))
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<PicUiModel>>) {
        communication.observe(owner = owner, observer = observer)
    }

    fun loadData() = viewModelScope.launch(dispatchers.io()) {
        communication.map(source = mapper.map(source = interactor.getData()))
    }

    fun loadMoreData(lastVisibleItemPosition: Int) {
        if (lastVisibleItemPosition != lastVisibleItemPos && interactor.needToLoadMoreData(
                lastVisibleItemPosition = lastVisibleItemPosition
            )
        ) {
            lastVisibleItemPos = lastVisibleItemPosition
            loadData()
        }
    }

}