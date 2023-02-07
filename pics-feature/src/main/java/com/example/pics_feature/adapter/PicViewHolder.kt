package com.example.pics_feature.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pics_feature.databinding.BottomErrorLayoutBinding
import com.example.pics_feature.databinding.FullsizeErrorLayoutBinding
import com.example.pics_feature.databinding.FullsizeLoaderLayoutBinding
import com.example.pics_feature.databinding.PicViewHolderBinding

abstract class PicBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    open fun onBind(model: PicUiModel) {}
}

class PicViewHolder(private val view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    private val binding = PicViewHolderBinding.bind(view)
    override fun onBind(model: PicUiModel) = with(binding) {
        view.setOnClickListener { clickListener.onClick() }
        model.show(textContainer = picDescriptionTv, imageContainer = picImageView)
    }
}

class FullSizeLoaderViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    private val binding = FullsizeLoaderLayoutBinding.bind(view)
    override fun onBind(model: PicUiModel) = clickListener.loadData()
}

class BottomLoaderViewHolder(view: View) : PicBaseViewHolder(view)

class FullSizeErrorViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    private val binding = FullsizeErrorLayoutBinding.bind(view)
    override fun onBind(model: PicUiModel) = with(binding) {
        fullsizeErrorTryAgainButton.setOnClickListener {
            clickListener.tryLoadDataAgain()
        }
        model.show(textContainer = fullsizeErrorTextView)
    }
}

class BottomErrorViewHolder(view: View, private val clickListener: PicsClickListener) :
    PicBaseViewHolder(view) {
    private val binding = BottomErrorLayoutBinding.bind(view)
    override fun onBind(model: PicUiModel) = with(binding) {
        bottomErrorTryAgainButton.setOnClickListener {
            clickListener.tryLoadMoreDataAgain()
        }
        model.show(textContainer = bottomErrorTextView)
    }
}