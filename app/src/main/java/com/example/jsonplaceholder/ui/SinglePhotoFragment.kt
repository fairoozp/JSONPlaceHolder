package com.example.jsonplaceholder.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.databinding.FragmentSinglePhotoBinding

class SinglePhotoFragment : Fragment() {
    private lateinit var binding: FragmentSinglePhotoBinding
    private lateinit var mContext: Context
    private val args: SinglePhotoFragmentArgs by navArgs()
    var photoUrl = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSinglePhotoBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoUrl = args.url
        Glide.with(mContext)
            .load(photoUrl)
            .override(600, 600)
            .placeholder(R.drawable.ic_load_image)
            .error(R.drawable.ic_load_failed_image)
            .into(binding.ivPhoto)
    }
}