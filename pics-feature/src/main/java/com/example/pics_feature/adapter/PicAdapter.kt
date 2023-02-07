package com.example.pics_feature.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.best.core.other.inflate
import com.example.pics_feature.R

class PicAdapter(private val clickListener: PicsClickListener) :
    ListAdapter<PicUiModel, PicBaseViewHolder>(PicUiModelDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicBaseViewHolder =
        when (viewType) {
            PicUiModelType.BASIC.ordinal -> PicViewHolder(
                parent.inflate(R.layout.pic_view_holder), clickListener
            )
            PicUiModelType.FULL_SIZE_LOADER.ordinal -> FullSizeLoaderViewHolder(
                parent.inflate(R.layout.fullsize_loader_layout), clickListener
            )
            PicUiModelType.FULL_SIZE_ERROR.ordinal -> FullSizeErrorViewHolder(
                parent.inflate(R.layout.fullsize_error_layout), clickListener
            )
            PicUiModelType.BOTTOM_ERROR.ordinal -> BottomErrorViewHolder(
                parent.inflate(R.layout.bottom_error_layout), clickListener
            )
            else -> BottomLoaderViewHolder(parent.inflate(R.layout.bottom_loader_layout))
        }

    override fun onBindViewHolder(holder: PicBaseViewHolder, position: Int) =
        holder.onBind(model = currentList[position])

    override fun getItemViewType(position: Int) = currentList[position].type.ordinal
}

class PicUiModelDiffUtilCallback : DiffUtil.ItemCallback<PicUiModel>() {
    override fun areItemsTheSame(oldItem: PicUiModel, newItem: PicUiModel): Boolean {
        return oldItem.type == newItem.type
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: PicUiModel, newItem: PicUiModel): Boolean {
        return oldItem == newItem
    }
}