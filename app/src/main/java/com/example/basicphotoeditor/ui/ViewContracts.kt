package com.example.basicphotoeditor.ui

import androidx.paging.PagingData
import com.example.basicphotoeditor.data.room.PostEntity
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface PostListViewContract: ViewContract {
    fun showPosts(posts: List<PostEntity>)
    fun showStreamPosts(posts: PagingData<PostEntity>)
}

interface ViewContract {

}

