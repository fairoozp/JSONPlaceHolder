package com.example.jsonplaceholder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.databinding.HomeAdapterBinding
import com.example.jsonplaceholder.mainModel.PostModel

class HomeAdapter(private var postModel: List<PostModel>, val listener: (Int) -> Unit): RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: HomeAdapterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(postModel: PostModel) {
            binding.tvTitleHome.text = postModel.title
            binding.tvBodyHome.text = postModel.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(HomeAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(postModel[position])
        holder.binding.homeRoot.setOnClickListener { postModel[position].id?.let { id -> listener(id) } }
    }

    override fun getItemCount(): Int {
        return postModel.size
    }

    fun setItem(data: List<PostModel>) {
        postModel = data
        this.notifyDataSetChanged()
    }
}