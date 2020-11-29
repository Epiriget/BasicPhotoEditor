package com.example.basicphotoeditor.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicphotoeditor.domain.FilterTransformation
import com.example.basicphotoeditor.domain.Post

class PostAdapter: PagingDataAdapter<Post, RecyclerView.ViewHolder>(POST_COMPARATOR) {
    var currentFilter: FilterTransformation.Filter = FilterTransformation.Filter.NONE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val postItem = getItem(position)
        postItem?.filter = currentFilter
        if(postItem != null) {
            (holder as PostViewHolder).bind(postItem)
        }
    }


    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}