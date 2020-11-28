package com.example.basicphotoeditor.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicphotoeditor.data.room.PostEntity
import com.example.basicphotoeditor.domain.FilterTransformation

//PagingDataAdapter
class PostAdapter: PagingDataAdapter<PostEntity, RecyclerView.ViewHolder>(POST_COMPARATOR) {
    var currentFilter: FilterTransformation.Filter = FilterTransformation.Filter.NONE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val postItem = getItem(position)
        if(postItem != null) {
            (holder as PostViewHolder).bind(postItem)
        }
    }



    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostEntity>() {
            override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean =
                oldItem == newItem
        }
    }
}