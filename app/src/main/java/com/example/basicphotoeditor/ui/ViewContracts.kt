package com.example.basicphotoeditor.ui

import androidx.paging.PagingData
import com.example.basicphotoeditor.data.room.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostListViewContract: ViewContract {
    fun showPosts(posts: List<PostEntity>)
    fun showStreamPosts(posts: Flow<PagingData<PostEntity>>)
}

interface ViewContract {

}

