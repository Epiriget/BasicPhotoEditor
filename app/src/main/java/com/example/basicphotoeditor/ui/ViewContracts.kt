package com.example.basicphotoeditor.ui

import androidx.paging.PagingData
import com.example.basicphotoeditor.data.room.PostEntity

interface PostListViewContract: ViewContract {
    fun showPosts(posts: List<PostEntity>)
    fun showStreamPosts(posts: PagingData<PostEntity>)
}

interface ViewContract {

}

