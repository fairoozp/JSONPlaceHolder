package com.example.jsonplaceholder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.databinding.AlbumAdapterBinding
import com.example.jsonplaceholder.mainModel.AlbumModel

class AlbumAdapter(private var albumModel: List<AlbumModel>, val listener: (Int) -> Unit): RecyclerView.Adapter<AlbumAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: AlbumAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(albumModel: AlbumModel) {
            binding.tvAlbumTitle.text = albumModel.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            AlbumAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(albumModel[position])
        holder.binding.homeRoot.setOnClickListener { albumModel[position].id?.let { id -> listener(id) } }
    }

    override fun getItemCount(): Int {
        return albumModel.size
    }

    fun setItem(data: List<AlbumModel>) {
        albumModel = data
        this.notifyDataSetChanged()
    }
}