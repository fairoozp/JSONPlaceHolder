package com.example.jsonplaceholder.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jsonplaceholder.R
import com.example.jsonplaceholder.databinding.PhotoAdapterBinding
import com.example.jsonplaceholder.mainModel.PhotoModel


class PhotoAdapter(private var photoModel: List<PhotoModel>, val listener: (String) -> Unit): RecyclerView.Adapter<PhotoAdapter.ItemViewHolder>() {
    lateinit var context: Context
    class ItemViewHolder(val binding: PhotoAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photoModel: PhotoModel, context: Context) {
            binding.tvTitlePhoto.text = photoModel.title
            Glide.with(context)
                .load(photoModel.thumbnailUrl)
                .override(150, 150)
                .placeholder(R.drawable.ic_load_image)
                .error(R.drawable.ic_load_failed_image)
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        return ItemViewHolder(
            PhotoAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(photoModel[position], context)
        holder.binding.homeRoot.setOnClickListener { photoModel[position].url?.let { url -> listener(url) } }
    }

    override fun getItemCount(): Int {
        return photoModel.size
    }

    fun setItem(data: List<PhotoModel>) {
        photoModel = data
        this.notifyDataSetChanged()
    }
}