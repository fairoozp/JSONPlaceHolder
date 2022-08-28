package com.example.jsonplaceholder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.jsonplaceholder.databinding.FragmentAlbumBinding
import com.example.jsonplaceholder.mainModel.AlbumModel
import com.example.jsonplaceholder.mainModel.PostModel
import com.example.jsonplaceholder.mainViewModel.MainViewModel
import com.example.jsonplaceholder.ui.adapter.AlbumAdapter
import com.example.jsonplaceholder.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var albumModel = ArrayList<AlbumModel>()
    private lateinit var albumAdapter: AlbumAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        albumAdapter = AlbumAdapter(albumModel) { id ->
            val action = AlbumFragmentDirections.actionAlbumFragmentToPhotoFragment().setPhotoId(id.toString())
            Navigation.findNavController(binding.root).navigate(action)
        }
        binding.rvHome.adapter = albumAdapter
    }

    private fun getData() {
        mainViewModel.getAlbum(viewLifecycleOwner)
        mainViewModel.albumValue.observe(viewLifecycleOwner) {
            albumModel.addAll(it)
            albumAdapter.setItem(albumModel)
        }
    }
}