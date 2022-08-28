package com.example.jsonplaceholder.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.databinding.HomeSubAdapterBinding
import com.example.jsonplaceholder.mainModel.CommentModel

class HomeSubAdapter(private var commentModel: List<CommentModel>): RecyclerView.Adapter<HomeSubAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val binding: HomeSubAdapterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(commentModel: CommentModel) {
            binding.tvEmailComment.text = commentModel.email
            binding.tvNameComment.text = commentModel.name
            binding.tvBodyComment.text = commentModel.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(HomeSubAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(commentModel[position])
    }

    override fun getItemCount(): Int {
        return commentModel.size
    }

    fun setItem(data: List<CommentModel>) {
        commentModel = data
        this.notifyDataSetChanged()
    }
}