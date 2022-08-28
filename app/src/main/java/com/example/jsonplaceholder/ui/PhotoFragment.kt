package com.example.jsonplaceholder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.databinding.FragmentPhotoBinding
import com.example.jsonplaceholder.mainModel.PhotoModel
import com.example.jsonplaceholder.mainViewModel.MainViewModel
import com.example.jsonplaceholder.ui.adapter.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment() {
    private lateinit var binding: FragmentPhotoBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var photoModel = ArrayList<PhotoModel>()
    private lateinit var photoAdapter: PhotoAdapter
    private val args: PhotoFragmentArgs by navArgs()
    var photoId = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoId = args.photoId
        getData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        photoAdapter = PhotoAdapter(photoModel) { url ->
            val action = PhotoFragmentDirections.actionPhotoFragmentToSinglePhotoFragment().setUrl(url)
            Navigation.findNavController(binding.root).navigate(action)
        }
        binding.rvHome.adapter = photoAdapter
    }

    private fun getData() {
        mainViewModel.getPhotos(viewLifecycleOwner, photoId)
        mainViewModel.photoValue.observe(viewLifecycleOwner) {
            photoModel.addAll(it)
            photoAdapter.setItem(photoModel)
        }
    }
}