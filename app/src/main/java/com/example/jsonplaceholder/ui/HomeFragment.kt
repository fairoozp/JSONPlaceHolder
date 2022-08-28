package com.example.jsonplaceholder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.jsonplaceholder.databinding.FragmentHomeBinding
import com.example.jsonplaceholder.mainModel.PostModel
import com.example.jsonplaceholder.mainViewModel.MainViewModel
import com.example.jsonplaceholder.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var postModel = ArrayList<PostModel>()
    private lateinit var homeAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        homeAdapter = HomeAdapter(postModel) { id ->
            val action = HomeFragmentDirections.actionHomeFragmentToHomeSubFragment().setId(id.toString())
            Navigation.findNavController(binding.root).navigate(action)
        }
        binding.rvHome.adapter = homeAdapter
    }

    private fun getData() {
        mainViewModel.getPost(viewLifecycleOwner)
        mainViewModel.postValue.observe(viewLifecycleOwner) {
            postModel.addAll(it)
            homeAdapter.setItem(postModel)
        }
    }
}