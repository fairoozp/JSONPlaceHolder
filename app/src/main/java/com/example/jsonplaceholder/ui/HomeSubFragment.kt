package com.example.jsonplaceholder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.jsonplaceholder.databinding.FragmentHomeSubBinding
import com.example.jsonplaceholder.mainModel.CommentModel
import com.example.jsonplaceholder.mainViewModel.MainViewModel
import com.example.jsonplaceholder.ui.adapter.HomeSubAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeSubFragment : Fragment() {
    private lateinit var binding: FragmentHomeSubBinding
    private val mainViewModel: MainViewModel by viewModels()
    var commentModel = ArrayList<CommentModel>()
    private lateinit var homeSubAdapter: HomeSubAdapter
    private val args: HomeSubFragmentArgs by navArgs()
    var postId = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeSubBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postId = args.id
        getData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        homeSubAdapter = HomeSubAdapter(commentModel)
        binding.rvHome.adapter = homeSubAdapter
    }

    private fun getData() {
        mainViewModel.getComment(viewLifecycleOwner, postId)
        mainViewModel.commentValue.observe(viewLifecycleOwner) {
            commentModel.addAll(it)
            homeSubAdapter.setItem(commentModel)
        }
    }
}