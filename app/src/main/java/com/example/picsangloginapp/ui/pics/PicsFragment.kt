package com.example.picsangloginapp.ui.pics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picsangloginapp.databinding.FragmentPicsBinding
import com.example.picsangloginapp.ui.pics.adapter.PicAdapter
import com.example.picsangloginapp.ui.pics.adapter.PicsClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PicsFragment : Fragment(), PicsClickListener {

    private var _binding: FragmentPicsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PicsViewModel by viewModels()
    private lateinit var picsAdapter: PicAdapter

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