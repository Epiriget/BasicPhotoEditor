package com.example.basicphotoeditor.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.basicphotoeditor.R

class PostsLoadStateViewHolder(
    view: View,
    retry: () -> Unit
): RecyclerView.ViewHolder(view) {
    private val retryButton: Button = view.findViewById(R.id.retry_button)
    private val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
    private val errorMessage: TextView = view.findViewById(R.id.error_msg)

    init {
        retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if(loadState is LoadState.Error) {
            errorMessage.text = loadState.error.localizedMessage
        }
        progressBar.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState !is LoadState.Loading
        errorMessage.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PostsLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.posts_load_state_footer_view_item, parent)
            return PostsLoadStateViewHolder(view, retry)
        }
    }

}