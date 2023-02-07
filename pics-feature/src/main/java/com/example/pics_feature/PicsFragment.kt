package com.example.pics_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.best.core.di.viewmodel.VmFactoryWrapper
import com.example.pics_feature.adapter.PicAdapter
import com.example.pics_feature.adapter.PicsClickListener
import com.example.pics_feature.databinding.FragmentPicsBinding
import com.example.pics_feature.di.PicsFeatureComponentHolder

class PicsFragment : Fragment(), PicsClickListener {

    private var _binding: FragmentPicsBinding? = null
    private val binding get() = _binding!!

    private lateinit var picsAdapter: PicAdapter

    private val vmFactoryWrapper = VmFactoryWrapper()
    private val viewModel: PicsViewModel by lazy {
        ViewModelProvider(this, vmFactoryWrapper.factory)[PicsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PicsFeatureComponentHolder.get().inject(vmFactoryWrapper = vmFactoryWrapper)
        setupAdapter()
        viewModel.observe(owner = viewLifecycleOwner) {
            picsAdapter.submitList(it)
        }
    }

    private fun setupAdapter() {
        picsAdapter = PicAdapter(clickListener = this)
        val linearLayoutManager = LinearLayoutManager(context)
        with(binding.picsRecyclerView) {
            layoutManager = linearLayoutManager
            adapter = picsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    viewModel.loadMoreData(
                        lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
                    )
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun loadData() {
        viewModel.loadData()
    }

    override fun tryLoadDataAgain() {
        viewModel.loadData()
    }

    override fun tryLoadMoreDataAgain() {
        viewModel.loadData()
    }
}